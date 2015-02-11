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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.api.MasterDto;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Master;
import de.yellow.medienverwaltung.database.entity.Release;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class MasterDao extends JdbcTemplate {
	
	/* Definition der Mapper-Klasse für Master-Objekte */
	private class MasterRowMapper implements RowMapper<Master> {
		public Master mapRow(ResultSet rs, int rowNum) throws SQLException {
			Master master = new Master();
			Artist artist = new Artist();
			Genre genre = new Genre();
			List<Subgenre> subgenres = new ArrayList<Subgenre>();
			List<Release> releases = new ArrayList<Release>();

			master.setMasterId(rs.getInt("master_id"));
			master.setTitle(rs.getString("title"));
			master.setReleaseDay(rs.getInt("release_day"));
			master.setReleaseMonth(rs.getInt("release_month"));
			master.setReleaseYear(rs.getInt("release_year"));
			master.setImageURL(rs.getString("image_url"));

			ArtistDao aDao = new ArtistDao();
			artist = aDao.getArtistById(rs.getInt("artist_id"));
			master.setArtist(artist);

			GenreDao gDao = new GenreDao();
			genre = gDao.getGenreById(rs.getInt("genre_id"));
			master.setGenre(genre);

			SubgenreDao sDao = new SubgenreDao();
			subgenres = sDao.getSubgenresByMasterId(master
					.getMasterId());
			master.setSubgenres(subgenres);

			ReleaseDao rDao = new ReleaseDao();
			releases = rDao.getReleasesByMasterId(master
					.getMasterId());
			master.setReleases(releases);

			return master;
		}
	}

	/* Logger */
	private static final Logger LOG = LoggerFactory.getLogger(MasterDao.class);

	/* Konstruktor */
	public MasterDao() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
		
		this.setDataSource(ds);
	}

	/* Fügt einen Master in die Datenbank ein und gibt die ID des neuen Master zurück. */
	public long insertMaster(MasterDto master) {

		// Artist prüfen: Ist der angegebene Name bekannt?
		ArtistDao aDao = new ArtistDao();
		final Artist artist = aDao.getArtistByName(master.getArtist());

		if ((artist == null) || (artist.getArtistId() == 0)) {
			throw new IllegalArgumentException(
					"Dieser Künstler ist uns nicht bekannt.");
		}

		final String title = master.getTitle();
		final String imageUrl = master.getUrl();
		final int genreId = master.getGenreId();

		// Prüfen, ob Master schon vorhanden (Titel und Artist)
		String checkSql = "SELECT master_id FROM master WHERE title = ? and artist_id = ?";
		Object[] checkParams = new Object[] { title, artist.getArtistId() };

		List<Integer> list = query(checkSql, checkParams, 
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	
						Integer id = rs.getInt("master_id");
						return id;
					}
				});

		if (list.size() > 0) {
			throw new IllegalArgumentException(
					"Master zu dieser Ver&ouml;ffentlichung ist bereits vorhanden.");
		}

		// TODO: Zusätzliche Validierung des Datums im Backend
		final Integer day = master.getReleaseDay();
		final Integer month = master.getReleaseMonth();
		final Integer year = master.getReleaseYear();

		// Master speichern & ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String masterSql = "INSERT INTO master(master_id, artist_id, title, release_day, release_month, "
				+ "release_year, image_url, genre_id) "
				+ "VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";

		update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(masterSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, artist.getArtistId());
				ps.setString(2, title);
				ps.setInt(3, day);
				ps.setInt(4, month);
				ps.setInt(5, year);
				ps.setString(6, imageUrl);
				ps.setInt(7, genreId);
				return ps;
			}
		}, keyHolder);

		long masterId = keyHolder.getKey().longValue();

		// Subgenres speichern
		if (master.getSubgenreIds() != null
				&& master.getSubgenreIds().size() != 0) {
			
			final String subgenreSql = "INSERT INTO master_subgenre(master_id, subgenre_id) VALUES(?, ?)";

			for (int subgenreId : master.getSubgenreIds()) {

				Object[] subgenreParams = new Object[] { masterId, subgenreId };
				update(subgenreSql, subgenreParams);

			}
		}

		LOG.debug("Master wurde eingefügt mit der id: " + masterId);

		return masterId;

	}

	/* Liefert ein Master-Objekt anhand der Master-ID */
	public Master getMasterById(long masterId) {

		String sql = "SELECT master_id, artist_id, title, release_day, release_month, release_year, "
				+ "image_url, genre_id "
				+ "FROM master WHERE master_id = ?";
		Object[] params = new Object[] { masterId };

		List<Master> masterList = query(sql, params, new MasterRowMapper());
		
		return masterList.get(0);
	}

	/* Liefert eine Liste von Master-Objekten anhand des Künstlernamens */ 
	public List<Master> getMastersByArtistName(String name) {

		List<Master> masterList = new ArrayList<Master>();

		// Artist prüfen: Ist der angegebene Name bekannt?
		// Finde alle Artists, die zum Suchbegriff passen.
		ArtistDao aDao = new ArtistDao();
		final List<Artist> artists = aDao.searchArtistByName(name);

		// Nichts gefunden: Return leere Liste
		if ((artists == null) || (artists.isEmpty())) {
			return masterList;
		}

		String sql = "SELECT master_id, artist_id, title, release_day, release_month, release_year, "
				+ "image_url, genre_id "
				+ "FROM master WHERE artist_id = ?";

		for (Artist artist : artists) {

			List<Master> mastersForArtist = new ArrayList<Master>();
			Object[] params = new Object[] { artist.getArtistId() };

			mastersForArtist = query(sql, params, new MasterRowMapper());
			
			masterList.addAll(mastersForArtist);
		}

		return masterList;
	}

	/* Liefert eine Liste von MasterDtos, die zu einer bestimmten Artist-ID passen */
	public List<MasterDto> getMastersByArtistId(long artistId) {
		
		List<MasterDto> masters = new ArrayList<MasterDto>();
		
		String sql = "SELECT * FROM master WHERE artist_id = ?";
		Object[] params = new Object[] { artistId };
		
		masters = query(sql, params,
				new RowMapper<MasterDto>() {
					public MasterDto mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						MasterDto master = new MasterDto();
						Artist artist = new Artist();
						Genre genre = new Genre();
						List<Subgenre> subgenres = new ArrayList<Subgenre>();
						List<Integer> subgenreIds = new ArrayList<Integer>();

						master.setMasterId(rs.getInt("master_id"));
						master.setArtistId(rs.getInt("artist_id"));
						master.setTitle(rs.getString("title"));
						master.setGenreId(rs.getInt("genre_id"));
						master.setReleaseDay(rs.getInt("release_day"));
						master.setReleaseMonth(rs.getInt("release_month"));
						master.setReleaseYear(rs.getInt("release_year"));
						master.setUrl(rs.getString("image_url"));
						
						ArtistDao aDao = new ArtistDao();
						artist = aDao.getArtistById(master.getArtistId()); 
						master.setArtist(artist.getName()); 
						
						GenreDao gDao = new GenreDao();
						genre = gDao.getGenreById(master.getGenreId());
						master.setGenre(genre.getName());

						SubgenreDao sDao = new SubgenreDao();
						subgenres = sDao.getSubgenresByMasterId(master.getMasterId());
						for (Subgenre subgenre : subgenres) {
							subgenreIds.add(subgenre.getSubgenreId());
						}
						master.setSubgenreIds(subgenreIds);
						
						return master;
					}
				});
		
		
		return masters;
		
	}
	
	/* Methode für die Suche: Liefert alle Master, deren Titel zum Suchstring passen.  */
	public List<Master> getMastersByTitle(String title) {

		List<Master> masters = new ArrayList<Master>();

		String searchString = '%' + title.trim().replace(' ', '%') + '%';

		String sql = "SELECT master_id, artist_id, title, release_day, release_month, release_year, "
				+ "image_url, genre_id "
				+ "FROM master WHERE title LIKE ?";
		Object[] params = new Object[] { searchString };
		
		masters = query(sql, params, new MasterRowMapper());
		
		return masters;
	}

}
