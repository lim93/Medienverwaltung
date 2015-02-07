package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Label;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class ArtistDao extends JdbcTemplate {
	
	/* Definition der Mapper-Klasse für Artist-Objekte */
	private class ArtistRowMapper implements RowMapper<Artist> {
		public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
			Artist artist = new Artist();
	
			artist.setArtistId(rs.getInt("artist_id"));
			artist.setName(rs.getString("name"));
			artist.setFormed(rs.getInt("formed"));
			artist.setFrom(rs.getString("from"));
			artist.setWebsite(rs.getString("website"));
		
			return artist;
		}
	}

	/* Logger */
	private static final Logger LOG = LoggerFactory.getLogger(ArtistDao.class);
	
	/* Konstruktor */
	public ArtistDao() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
		
		this.setDataSource(ds);
	}

	/* Liefert ein Artist-Objekt anhand des Namens.
	 * Wirft eine Exception, falls der Artist noch nicht vorhanden ist. */
	public Artist getArtistByName(String name) {

		String sql = "SELECT * FROM artist WHERE name = ?";

		Object[] params = new Object[] { name };
		
		try {
			Artist artist = queryForObject(sql, params, new ArtistRowMapper());
			return artist;
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("Der K&uuml;nstler '" + name
					+ "' ist uns nicht bekannt.");
		}
	}

	/* Methode für die Suche: Liefert eine Liste von Artists, die zum Suchstring passen. */
	public List<Artist> searchArtistByName(String name) {

		List<Artist> artists = new ArrayList<Artist>();

		// Eingabe des Benutzers in Search-String umwandeln, z.B.
		// "   die ärzte   " -> "%die%ärzte%"
		String searchString = '%' + name.trim().replace(' ', '%') + '%';

		String sql = "SELECT * FROM artist WHERE name LIKE ?";
		Object[] params = new Object[] { searchString };
		
		artists = query(sql, params, new ArtistRowMapper());
		
		return artists;
	}

	/* Liefert ein Artist-Objekt anhand der Artist-ID */
	public Artist getArtistById(int id) {

		Artist artist = new Artist();
		
		String sql = "SELECT * FROM artist WHERE artist_id = ?";
		Object[] params = new Object[] { id };

		artist = queryForObject(sql, params, new ArtistRowMapper());

		return artist;
	}

	/* Liefert ein Artist-DTO anhand der Artist-ID */
	public ArtistDto getArtistDtoById(int id) {

		ArtistDto artistDto = new ArtistDto();
		
		String sql = "SELECT * FROM artist WHERE artist_id = ?";
		Object[] params = new Object[] { id };

		Artist artist = queryForObject(sql, params,	new ArtistRowMapper());

		artistDto.setArtistId(artist.getArtistId());
		artistDto.setName(artist.getName());
		artistDto.setFormed(artist.getFormed());
		artistDto.setFrom(artist.getFrom());
		artistDto.setWebsite(artist.getWebsite());

		GenreDao gDao = new GenreDao();
		List<Genre> genres = gDao.getGenresByArtistId(artist.getArtistId());

		SubgenreDao sgDao = new SubgenreDao();
		List<Subgenre> subgenres = sgDao.getSubgenresByArtistId(artist
				.getArtistId());

		artistDto.setGenres(genres);
		artistDto.setSubgenres(subgenres);

		return artistDto;
	}

	/* Liefert eine Liste aller Artists, die bei einem bestimmten Label 
	 * veröffentlicht haben */
	public List<Artist> getArtistsByLabelId(long labelId) {

		List<Artist> artists = new ArrayList<Artist>();

		String sql = "SELECT DISTINCT a.artist_id, a.name, a.formed, a.from, a.website "
				+ "FROM artist AS a "
				+ "JOIN master AS m ON a.artist_id = m.artist_id "
				+ "JOIN media.release AS r ON m.master_id = r.master_id "
				+ "WHERE r.label_id = ?";

		Object[] params = new Object[] { labelId };
		
		artists = query(sql, params, new ArtistRowMapper());
		
		return artists;
	}

	/* Liefert die am häufigsten auftretenden Artists in der Sammlung eines Users */
	public Map<Integer, Artist> getTopArtistsByUserId(int userId, int limit) {

		Map<Integer, Artist> artists = new HashMap<Integer, Artist>();

		List<Artist> artistList = new ArrayList<Artist>();

		String sql = "SELECT a.artist_id, a.name, a.formed, a.from, a.website "
				+ "FROM artist AS a "
				+ "JOIN master AS m ON a.artist_id = m.artist_id "
				+ "JOIN media.release AS r ON m.master_id = r.master_id "
				+ "JOIN collection_item AS c on r.release_id = c.release_id "
				+ "WHERE c.user_id = ? " + "GROUP by a.artist_id "
				+ "ORDER by COUNT(a.artist_id) desc " + "LIMIT ?;";

		Object[] params = new Object[] { userId, limit };
		
		artistList = query(sql, params, new ArtistRowMapper());
		
		int counter = 0;
		for (Artist artist : artistList) {
			counter++;
			artists.put(counter, artist);
		}

		return artists;
	}

	/* Fügt einen neuen Artist in die Datenbank ein */
	public long insert(ArtistDto artist) {

		final String name = artist.getName();
		final int formed = artist.getFormed();
		final String from = artist.getFrom();
		final String website = artist.getWebsite();

		// Prüfen, ob schon existent
		try {
			Artist controlArtist = getArtistByName(name);

			if (controlArtist != null) {
				throw new IllegalStateException(
						"Dieser Künstler ist bereits bekannt.");
			}
		} catch (IllegalArgumentException e) {
			// alles ok
		}

		// Artist speichern & ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String sql = "INSERT INTO artist(artist_id, name, formed, artist.from, website) VALUES(NULL, ?, ?, ?, ?)";

		update(new PreparedStatementCreator() {
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
