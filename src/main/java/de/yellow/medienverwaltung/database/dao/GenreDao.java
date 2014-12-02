package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class GenreDao {

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
	 * einem {@link RowMapper} auf die Klasse {@link Genre} gemappt.
	 * 
	 * @return
	 */
	public List<Genre> getAllGenres() {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Genre> list = jdbcTemplate.query("select * from genre",
				new RowMapper<Genre>() {
			public Genre mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Genre genre = new Genre();

				genre.setGenreID(rs.getInt("genre_id"));
				genre.setName(rs.getString("name"));

				return genre;
			}
		});

		return list;
	}
}
