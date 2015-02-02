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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.api.UserDto;
import de.yellow.medienverwaltung.database.entity.User;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class UserDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

	/**
	 * Holt eine {@link DataSource} aus der {@link ConnectionFactory}
	 * 
	 * @return
	 */
	private DataSource getDataSource() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds != null) {
			return ds;
		} else {
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

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

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
		
		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select user_id, user_name, email from user where user_id = ?";

		User user = new User();

		user = (User) jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<User>(User.class));

		return user;

	}
	
	public User getUserByName(String name) {
		
		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select user_id, user_name, email from user where user_name = ?";

		User user = new User();

		user = (User) jdbcTemplate.queryForObject(sql, new Object[] { name },
				new BeanPropertyRowMapper<User>(User.class));

		return user;
	
	}
	
	public long insert(UserDto user) {
		
		DataSource dataSource = getDataSource();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		final String userName = user.getUserName();
		final String password = user.getPassword();
		final String email = user.getEmail();
		
		// Prüfen, ob Benutzername noch verfügbar ist
		String isAvailableSql = "SELECT user_id FROM user WHERE user_name = ?";
		
		List<Integer> isAvailableList = jdbcTemplate.query(isAvailableSql, new Object[] { userName }, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
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
				PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
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
	
}