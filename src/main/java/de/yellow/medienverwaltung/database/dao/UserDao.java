package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

		user = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<User>(User.class));

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

}