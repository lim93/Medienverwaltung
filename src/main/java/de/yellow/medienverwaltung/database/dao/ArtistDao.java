package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.api.ArtistDto;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class ArtistDao {

	private static final Logger LOG = LoggerFactory.getLogger(ArtistDao.class);

	private DataSource ds;

	public ArtistDao() {
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
	 * einem {@link RowMapper} auf die Klasse {@link Artist} gemappt.
	 * 
	 * @return
	 */
	public List<Artist> getAllArtists() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

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

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

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

	public List<Artist> searchArtistByName(String name) {

		// Diese Methode zum Suchen.

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		List<Artist> artists = new ArrayList<Artist>();

		// Eingabe des Benutzers in Search-String umwandeln, z.B.
		// "   die ärzte   " -> "%die%ärzte%"
		String searchString = '%' + name.trim().replace(' ', '%') + '%';

		String sql = "select * from artist where name LIKE ?";

		artists = jdbcTemplate.query(sql, new Object[] { searchString },
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

		return artists;

		// try {
		// Artist artist = jdbcTemplate.queryForObject(sql,
		// new Object[] { searchString }, new BeanPropertyRowMapper<Artist>(
		// Artist.class));
		//
		// return artist;
		// } catch (EmptyResultDataAccessException e) {
		// return null;
		// }
	}

	public Artist getArtistById(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select * from artist where artist_id = ?";

		Artist artist = new Artist();

		artist = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Artist>(Artist.class));

		return artist;
	}

	public List<Artist> getArtistsByLabelId(long labelId) {
		
		JdbcTemplate jt = new JdbcTemplate(ds);
		
		List<Artist> artists = new ArrayList<Artist>();
		
		String sql = "SELECT DISTINCT a.artist_id, a.name, a.formed, a.from, a.website "
				   + "FROM artist AS a "
				   + "JOIN master AS m ON a.artist_id = m.artist_id "
				   + "JOIN media.release AS r ON m.master_id = r.master_id "
				   + "WHERE r.label_id = ?";
		
		artists = jt.query(sql, new Object[] { labelId }, new RowMapper<Artist>() {
			public Artist mapRow(ResultSet rs, int rowNum) throws SQLException{
				Artist artist = new Artist();
				
				artist.setArtistId(rs.getInt("artist_id"));
				artist.setName(rs.getString("name"));
				artist.setFormed(rs.getInt("formed"));
				artist.setFrom(rs.getString("from"));
				artist.setWebsite(rs.getString("website"));
				
				return artist;
			}
		});
		
		return artists;
	}
	
	public long insert(ArtistDto artist) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		final String name = artist.getName();
		final int formed = artist.getFormed();
		final String from = artist.getFrom();
		final String website = artist.getWebsite();

		// Prüfen, ob Artist schon vorhanden <- notwendig?
		// String artistSql =
		// "SELECT artist_id FROM artist WHERE name = ? and formed = ?";
		//
		// List<Integer> list = jdbcTemplate.query(artistSql, new Object[] {
		// name,
		// formed }, new RowMapper<Integer>() {
		// public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		//
		// Integer id = rs.getInt("artist_id");
		//
		// return id;
		// }
		// });

		// if (list.size() != 0) {
		// throw new IllegalArgumentException(
		// "Artist ist bereits vorhanden.");
		// }

		// Artist speichern & ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String sql = "INSERT INTO artist(artist_id, name, formed, artist.from, website) VALUES(NULL, ?, ?, ?, ?)";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setInt(2, formed);
				ps.setString(3, from);
				ps.setString(4, website);
				return ps;
			}
		}, keyHolder);

		long artistId = keyHolder.getKey().longValue();

		LOG.debug("Artist wurde eingefügt mit der id: " + artistId);

		return artistId;

	}

}
