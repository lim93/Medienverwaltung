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

	private DataSource ds;

	public SubgenreDao() {
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
	 * einem {@link RowMapper} auf die Klasse {@link Subgenre} gemappt.
	 * 
	 * @return
	 */
	public List<Subgenre> getAllSubgenres() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

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

	public List<Subgenre> getSubgenresByMasterId(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "SELECT m.subgenre_id, s.genre_id, s.name FROM master_subgenre m JOIN subgenre s on m.subgenre_id = s.subgenre_id WHERE master_id = ?";

		List<Subgenre> subgenres = jdbcTemplate.query(sql, new Object[] { id },
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

		return subgenres;

	}

	public List<Subgenre> getSubgenresByArtistId(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "SELECT distinct ms.subgenre_id, s.genre_id, s.name FROM master_subgenre ms "
				+ "JOIN subgenre s on ms.subgenre_id = s.subgenre_id "
				+ "JOIN master m on ms.master_id = m.master_id "
				+ "WHERE m.artist_id = ?";

		List<Subgenre> subgenres = jdbcTemplate.query(sql, new Object[] { id },
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

		return subgenres;

	}
}
