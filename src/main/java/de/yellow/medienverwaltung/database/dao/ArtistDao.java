package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class ArtistDao {

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
	 * einem {@link RowMapper} auf die Klasse {@link Artist} gemappt.
	 * 
	 * @return
	 */
	public List<Artist> getAllArtists() {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Artist> list = jdbcTemplate.query("select * from artist",
				new RowMapper<Artist>() {
			public Artist mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Artist artist = new Artist();

				artist.setArtistId(rs.getInt("artist_id"));
				artist.setName(rs.getString("name"));
				artist.setFormed(rs.getInt("formed"));
				artist.setFrom(rs.getString("from"));
				artist.setWebsite(rs.getString("website"));

				return artist;
			}
		});

		return list;
	}

	public Artist getArtistByName(String name) {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from artist where name = ?";

		try {
			Artist artist = jdbcTemplate.queryForObject(sql,
					new Object[] { name }, new BeanPropertyRowMapper<Artist>(
							Artist.class));

			return artist;
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("Der K&uuml;nstler '" + name
					+ "' ist uns nicht bekannt.");
		}
	}

	public Artist getArtistById(int id) {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from artist where artist_id = ?";

		Artist artist = new Artist();

		artist = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Artist>(Artist.class));

		return artist;
	}
}
