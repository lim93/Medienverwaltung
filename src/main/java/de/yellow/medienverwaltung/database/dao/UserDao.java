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
import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Release;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.entity.User;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class UserDao {

	private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

	private DataSource ds;

	public UserDao() {
		ConnectionFactory factory = new ConnectionFactory();

		ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
	}

	/**
	 * Holt eine DataSource und erstellt daraus ein {@link JdbcTemplate}. Damit
	 * wird eine Abfrage gegen die Datenbank gestartet. Das Ergebnis wird mit
	 * einem {@link RowMapper} auf die Klasse {@link User} gemappt.
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		List<User> list = jdbcTemplate.query("select * from user",
				new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				User user = new User();

				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));

				return user;
			}
		});

		return list;
	}

	public User getUserById(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select user_id, user_name, email from user where user_id = ?";

		User user = new User();
		UserDto userDto = new UserDto();

		userDto = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<UserDto>(UserDto.class));

		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		// Auch wenn nur der Hash gespeichert ist, wird das Passwort explizit
		// nicht übrtragen!
		user.setPassword(null);

		ReleaseDao rDao = new ReleaseDao();

		List<Release> releaseList = rDao.getReleasesByUserId(id);
		user.setCollection(releaseList);

		Map<Integer, Genre> userGenres = new HashMap<Integer, Genre>();
		Map<Integer, Subgenre> userSubgenres = new HashMap<Integer, Subgenre>();
		Map<Integer, Artist> userArtists = new HashMap<Integer, Artist>();

		GenreDao gDao = new GenreDao();
		SubgenreDao sDao = new SubgenreDao();

		// TODO: Bessere Auswertung: SELECT ... ORDER BY COUNT(X) DESC LIMIT ...
		for (Release release : releaseList) {
			int masterId = release.getMasterId();

			Genre g = gDao.getGenreByMasterId(masterId);
			userGenres.put(g.getGenreId(), g);

			List<Subgenre> sList = sDao.getSubgenresByMasterId(masterId);

			for (Subgenre subgenre : sList) {
				userSubgenres.put(subgenre.getSubgenreId(), subgenre);
			}

			userArtists.put(release.getArtist().getArtistId(),
					release.getArtist());

		}

		user.setUseGenres(userGenres.values());
		user.setUserSubgenres(userSubgenres.values());
		user.setUserArtists(userArtists.values());

		return user;

	}

	public User getUserByName(String name) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select user_id, user_name, email from user where user_name = ?";

		User user = new User();

		user = jdbcTemplate.queryForObject(sql, new Object[] { name },
				new BeanPropertyRowMapper<User>(User.class));

		return user;

	}

	public long insert(UserDto user) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		final String userName = user.getUserName();
		final String password = user.getPassword();
		final String email = user.getEmail();

		// Prüfen, ob Benutzername noch verfügbar ist
		String isAvailableSql = "SELECT user_id FROM user WHERE user_name = ?";

		List<Integer> isAvailableList = jdbcTemplate.query(isAvailableSql,
				new Object[] { userName }, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Integer id = rs.getInt("user_id");
				return id;
			}
		});

		if (isAvailableList.size() != 0) {
			throw new IllegalArgumentException("Benutzername bereits vergeben.");
		}

		// User einfügen und ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String insertSql = "INSERT INTO user(user_id, user_name, password, email) VALUES(NULL, ?, ?, ?)";

		jdbcTemplate.update(new PreparedStatementCreator() {

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

	public long validateLogin(Login login) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		Long userId = new Long(0);

		final String userName = login.getUsername();
		final String password = login.getPassword();

		final String sql = "SELECT user_id FROM user WHERE user_name = ? AND password = ?";

		try {
			userId = jdbcTemplate.queryForObject(sql, new Object[] { userName,
					password }, Long.class);
		} catch (IncorrectResultSizeDataAccessException e) {
			userId = 0L;
		} catch (DataAccessException e) {

		}

		LOG.debug("Nach Login-Versuch: UserID: " + userId);

		return userId;
	}

	public boolean hasInCollection(final int userId, final int releaseId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		// Prüfen, ob Kombination aus user und version vorhanden
		String inCollectionSql = "SELECT coll_item_id FROM collection_item WHERE user_id = ? and release_id = ?";

		List<Integer> list = jdbcTemplate.query(inCollectionSql, new Object[] {
				userId, releaseId }, new RowMapper<Integer>() {
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

	public Long addToCollection(final int userId, final int releaseId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		// Prüfen, ob Kombination aus user und version schon vorhanden
		String inCollectionSql = "SELECT coll_item_id FROM collection_item WHERE user_id = ? and release_id = ?";

		List<Integer> list = jdbcTemplate.query(inCollectionSql, new Object[] {
				userId, releaseId }, new RowMapper<Integer>() {
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

		jdbcTemplate.update(new PreparedStatementCreator() {
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

	public int deleteFromCollection(final int userId, final int releaseId) {

		JdbcTemplate jt = new JdbcTemplate(ds);

		int rowsAffected = 0;

		String sql = "DELETE FROM collection_item WHERE user_id = ? AND release_id = ?";

		Object[] params = new Object[] { userId, releaseId };

		try {
			rowsAffected = jt.update(sql, params);
			return rowsAffected;
		} catch (DataAccessException e) {
			LOG.debug("Problem beim Löschen aus der Sammlung: "
					+ e.getStackTrace());
			return -1;
		}

	}

}