package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.User;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class UserDao {

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
	
}