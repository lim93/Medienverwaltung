package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class SubgenreDao {

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
	 * einem {@link RowMapper} auf die Klasse {@link Subgenre} gemappt.
	 * 
	 * @return
	 */
	public List<Subgenre> getAllSubgenres() {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Subgenre> list = jdbcTemplate.query("select * from subgenre",
				new RowMapper<Subgenre>() {
			public Subgenre mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Subgenre subgenre = new Subgenre();

				subgenre.setSubgenreId(rs.getInt("subgenre_id"));
				subgenre.setGenreId(rs.getInt("genre_id"));
				subgenre.setName(rs.getString("name"));

				return subgenre;
			}
		});

		return list;
	}
}
