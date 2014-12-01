package de.yellow.medienverwaltung.database.util;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Factory-Klasse zum erzeugen einer {@link DataSource}. Mit der
 * {@link DataSource} werden {@link JdbcTemplate}s erzeugt.
 * 
 * @author krispin
 *
 */
public class ConnectionFactory {

	// TODO: In properties auslagern!
	private String host = "localhost";
	private String db = "Medienverwaltung";
	private String port = "3306";
	private String user = "root";
	private String pw = "";

	public DataSource getDataSource() {

		// TODO: Logger statt System.out
		System.out
				.println("Aus den Parametern ein DataSource für die Datenbank erstellen");

		String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

		DriverManagerDataSource dataSource = new DriverManagerDataSource(url,
				user, pw);
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}

}
