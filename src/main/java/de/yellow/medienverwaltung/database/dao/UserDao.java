package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.api.Login;
import de.yellow.medienverwaltung.api.UserDto;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Release;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.entity.User;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class UserDao extends JdbcTemplate {

	/* Logger */
	private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

	/* Konstruktor */
	public UserDao() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
		
		this.setDataSource(ds);
	}

	/* Liefert ein User-Objekt zu der übergebenen User-ID */
	public User getUserById(int userId) {

		User user = new User();
		UserDto userDto = new UserDto();
		
		String sql = "SELECT user_id, user_name, email FROM user WHERE user_id = ?";
		Object[] params = new Object[] { userId };

		userDto = queryForObject(sql, params,
				new BeanPropertyRowMapper<UserDto>(UserDto.class));

		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		// Auch wenn nur der Hash gespeichert ist, wird das Passwort explizit
		// nicht übrtragen!
		user.setPassword(null);

		ReleaseDao rDao = new ReleaseDao();

		List<Release> releaseList = rDao.getReleasesByUserId(userId);
		user.setCollection(releaseList);

		SubgenreDao sDao = new SubgenreDao();
		Map<Integer, Subgenre> userSubgenres = new HashMap<Integer, Subgenre>();
		userSubgenres = sDao.getTopSubgenresByUserId(user.getUserId(), 10);

		Map<Integer, Artist> userArtists = new HashMap<Integer, Artist>();
		ArtistDao aDao = new ArtistDao();
		userArtists = aDao.getTopArtistsByUserId(user.getUserId(), 10);

		user.setUserSubgenres(userSubgenres.values());
		user.setUserArtists(userArtists.values());

		return user;
	}

	/* Liefert ein User-Objekt zu dem übergebenen Namen */
	public User getUserByName(String name) {

		User user = new User();
		
		String sql = "SELECT user_id, user_name, email FROM user WHERE user_name = ?";
		Object[] params = new Object[] { name };

		user = queryForObject(sql, params,
				new BeanPropertyRowMapper<User>(User.class));

		return user;
	}

	/* Fügt einen User (UserDto) in die Datenbank ein */
	public long insert(UserDto user) {

		final String userName = user.getUserName();
		final String password = user.getPassword();
		final String email = user.getEmail();

		// Prüfen, ob Benutzername noch verfügbar ist
		String isAvailableSql = "SELECT user_id FROM user WHERE user_name = ?";
		Object[] isAvailableParams = new Object[] { userName };
		
		List<Integer> isAvailableList = query(isAvailableSql,
				isAvailableParams, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Integer id = rs.getInt("user_id");
				return id;
			}
		});

		if (isAvailableList.size() > 0) {
			throw new IllegalArgumentException("Benutzername bereits vergeben.");
		}

		// User einfügen und ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String insertSql = "INSERT INTO user(user_id, user_name, password, email) VALUES(NULL, ?, ?, ?)";

		update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(insertSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, userName);
				ps.setString(2, password);
				ps.setString(3, email);
				return ps;
			}
		}, keyHolder);

		long userId = keyHolder.getKey().longValue();

		LOG.debug("User wurde eingefügt mit der id: " + userId);

		return userId;
	}

	/* Prüft, ob Benutzername vorhanden und Kennwort richtig sind. */
	public long validateLogin(Login login) {

		Long userId = new Long(0);

		final String userName = login.getUsername();
		final String password = login.getPassword();

		final String sql = "SELECT user_id FROM user WHERE user_name = ? AND password = ?";
		Object[] params = new Object[] { userName, password };

		try {
			userId = queryForObject(sql, params, Long.class);
		} catch (IncorrectResultSizeDataAccessException e) {
			userId = 0L;
		} catch (DataAccessException e) {

		}

		LOG.debug("Nach Login-Versuch: UserID: " + userId);

		return userId;
	}

	/* Prüft, ob der User userId das Release releaseId in seiner Sammlung hat. */ 
	public boolean hasInCollection(final int userId, final int releaseId) {

		// Prüfen, ob Kombination aus user und version vorhanden
		String inCollectionSql = "SELECT coll_item_id FROM collection_item WHERE user_id = ? and release_id = ?";
		Object[] params = new Object[] { userId, releaseId };		
		
		List<Integer> list = query(inCollectionSql, params,
				new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Integer id = rs.getInt("coll_item_id");

				return id;
			}
		});

		if (list.size() != 0) {
			return true;
		}
		return false;

	}

	/* Fügt ein Release als CollectionItem zur Sammlung des Users hinzu. */
	public Long addToCollection(final int userId, final int releaseId) {

		// Prüfen, ob Kombination aus user und version schon vorhanden
		String inCollectionSql = "SELECT coll_item_id FROM collection_item WHERE user_id = ? and release_id = ?";
		Object[] params = new Object[] { userId, releaseId };

		List<Integer> list = query(inCollectionSql, params, 
				new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Integer id = rs.getInt("coll_item_id");

				return id;
			}
		});

		if (list.size() != 0) {
			throw new IllegalArgumentException(
					"Diese Ver&ouml;ffentlichung ist bereits in Ihrer Sammlung.");
		}

		// speichern & ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String insertSql = "INSERT INTO collection_item(user_id, release_id) VALUES(?, ?)";

		update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(insertSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.setInt(2, releaseId);
				return ps;
			}
		}, keyHolder);

		long collItemId = keyHolder.getKey().longValue();

		return collItemId;

	}

	/* Löscht ein Release aus der Sammlung des Users */
	public int deleteFromCollection(final int userId, final int releaseId) {

		int rowsAffected = 0;

		String sql = "DELETE FROM collection_item WHERE user_id = ? AND release_id = ?";
		Object[] params = new Object[] { userId, releaseId };

		try {
			rowsAffected = update(sql, params);
			return rowsAffected;
		} catch (DataAccessException e) {
			LOG.debug("Problem beim Löschen aus der Sammlung: "
					+ e.getStackTrace());
			return -1;
		}
	}

}
