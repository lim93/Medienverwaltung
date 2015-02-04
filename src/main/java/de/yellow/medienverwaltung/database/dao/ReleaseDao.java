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

import de.yellow.medienverwaltung.api.ReleaseDto;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Format;
import de.yellow.medienverwaltung.database.entity.Label;
import de.yellow.medienverwaltung.database.entity.Master;
import de.yellow.medienverwaltung.database.entity.Release;
import de.yellow.medienverwaltung.database.entity.Track;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class ReleaseDao {

	private static final Logger LOG = LoggerFactory.getLogger(ReleaseDao.class);

	private DataSource ds;

	public ReleaseDao() {
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
	 * einem {@link RowMapper} auf die Klasse {@link Release} gemappt.
	 * 
	 * @return
	 */
	public List<Release> getAllReleases() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		List<Release> list = jdbcTemplate.query("select * from release",
				new RowMapper<Release>() {
					public Release mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Release release = new Release();

						release.setReleaseId(rs.getInt("release_id"));
						release.setMasterId(rs.getInt("master_id"));
						// release.setLabelId(rs.getInt("label_id"));
						// release.setFormatId(rs.getInt("format_id"));
						release.setReleaseDay(rs.getInt("release_day"));
						release.setReleaseMonth(rs.getInt("release_month"));
						release.setReleaseYear(rs.getInt("release_year"));
						release.setCatalogNo(rs.getString("catalog_no"));
						release.setLabelCode(rs.getString("label_code"));
						release.setBarcode(rs.getString("barcode"));
						release.setComment(rs.getString("comment"));

						return release;
					}
				});

		return list;
	}

	public Release getReleaseById(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select * from media.release where release_id = ?";

		List<Release> list = jdbcTemplate.query(sql, new Object[] { id },
				new RowMapper<Release>() {
					public Release mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Release release = new Release();

						release.setReleaseId(rs.getInt("release_id"));
						release.setMasterId(rs.getInt("master_id"));
						release.setReleaseDay(rs.getInt("release_day"));
						release.setReleaseMonth(rs.getInt("release_month"));
						release.setReleaseYear(rs.getInt("release_year"));
						release.setCatalogNo(rs.getString("catalog_no"));
						release.setLabelCode(rs.getString("label_code"));
						release.setBarcode(rs.getString("barcode"));
						release.setComment(rs.getString("comment"));

						LabelDao lDao = new LabelDao();
						Label label = lDao.getLabelById(rs.getInt("label_id"));
						release.setLabel(label);

						FormatDao fDao = new FormatDao();
						Format format = fDao.getFormatById(rs
								.getInt("format_id"));
						release.setFormat(format);

						TrackDao tDao = new TrackDao();
						List<Track> tracklist = tDao
								.getTracklistByReleaseId(release.getReleaseId());
						release.setTracklist(tracklist);

						return release;
					}
				});

		return list.get(0);

	}

	public List<Release> getReleasesByMasterId(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select * from media.release where master_id = ?";

		List<Release> list = jdbcTemplate.query(sql, new Object[] { id },
				new RowMapper<Release>() {
					public Release mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Release release = new Release();

						release.setReleaseId(rs.getInt("release_id"));
						release.setMasterId(rs.getInt("master_id"));
						release.setReleaseDay(rs.getInt("release_day"));
						release.setReleaseMonth(rs.getInt("release_month"));
						release.setReleaseYear(rs.getInt("release_year"));
						release.setCatalogNo(rs.getString("catalog_no"));
						release.setLabelCode(rs.getString("label_code"));
						release.setBarcode(rs.getString("barcode"));
						release.setComment(rs.getString("comment"));

						LabelDao lDao = new LabelDao();
						Label label = lDao.getLabelById(rs.getInt("label_id"));
						release.setLabel(label);

						FormatDao fDao = new FormatDao();
						Format format = fDao.getFormatById(rs
								.getInt("format_id"));
						release.setFormat(format);

						TrackDao tDao = new TrackDao();
						List<Track> tracklist = tDao
								.getTracklistByReleaseId(release.getReleaseId());
						release.setTracklist(tracklist);

						return release;
					}
				});

		return list;

	}

	public List<Release> getReleasesByUserId(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select * from media.release r "
				+ "join collection_item c on r.release_id = c.release_id "
				+ "join master m on r.master_id = m.master_id "
				+ "where c.user_id = ?";

		List<Release> list = jdbcTemplate.query(sql, new Object[] { id },
				new RowMapper<Release>() {
					public Release mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Release release = new Release();

						release.setReleaseId(rs.getInt("release_id"));
						release.setMasterId(rs.getInt("master_id"));
						release.setTitle(rs.getString("title"));
						release.setReleaseDay(rs.getInt("release_day"));
						release.setReleaseMonth(rs.getInt("release_month"));
						release.setReleaseYear(rs.getInt("release_year"));
						release.setCatalogNo(rs.getString("catalog_no"));
						release.setLabelCode(rs.getString("label_code"));
						release.setBarcode(rs.getString("barcode"));
						release.setComment(rs.getString("comment"));
						release.setImageURL(rs.getString("image_url"));

						LabelDao lDao = new LabelDao();
						Label label = lDao.getLabelById(rs.getInt("label_id"));
						release.setLabel(label);

						FormatDao fDao = new FormatDao();
						Format format = fDao.getFormatById(rs
								.getInt("format_id"));
						release.setFormat(format);

						TrackDao tDao = new TrackDao();
						List<Track> tracklist = tDao
								.getTracklistByReleaseId(release.getReleaseId());
						release.setTracklist(tracklist);

						ArtistDao aDao = new ArtistDao();
						Artist artist = aDao.getArtistById(rs
								.getInt("artist_id"));
						release.setArtist(artist);

						return release;
					}
				});

		return list;

	}

	public long insertRelease(ReleaseDto release) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		final int masterId = release.getMasterId();
		final int formatId = release.getFormatId();
		final String catalogNumber = release.getCatalogNo();
		final String labelCode = release.getLabelcode();
		final String barcode = release.getBarcode();
		final String comment = release.getComment();

		final String labelName = release.getLabel();

		LabelDao lDao = new LabelDao();
		final Label label = lDao.getLabelByName(labelName);

		if ((label == null) || (label.getLabelId() == 0)) {
			throw new IllegalArgumentException(
					"Dieses Label ist uns nicht bekannt.");
		}

		final int labelId = label.getLabelId();

		// TODO: Zusätzliche Validierung des Datums im Backend
		final Integer day = release.getReleaseDay();
		final Integer month = release.getReleaseMonth();
		final Integer year = release.getReleaseYear();

		// Release speichern & ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String releaseSql = "INSERT INTO `media`.`release` (`release_id`, `master_id`, `label_id`, `format_id`, `release_day`, `release_month`, `release_year`, `catalog_no`, `label_code`, `barcode`, `comment`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(releaseSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, masterId);
				ps.setInt(2, labelId);
				ps.setInt(3, formatId);
				ps.setInt(4, day);
				ps.setInt(5, month);
				ps.setInt(6, year);
				ps.setString(7, catalogNumber);
				ps.setString(8, labelCode);
				ps.setString(9, barcode);
				ps.setString(10, comment);
				return ps;
			}
		}, keyHolder);

		long releaseId = keyHolder.getKey().longValue();

		// Tracks speichern
		TrackDao tDao = new TrackDao();
		for (Track track : release.getTracklist()) {

			long trackId = tDao.insertTrack(track);

			final String tracklistSql = "INSERT INTO tracklist_item(release_id, track_id) VALUES(?, ?)";

			Object[] params = new Object[] { releaseId, trackId };

			jdbcTemplate.update(tracklistSql, params);
		}

		LOG.debug("Release wurde eingefügt mit der id: " + releaseId);

		return releaseId;

	}

	public List<Release> getReleasesByLabelId(long labelId) {
		
		JdbcTemplate jt = new JdbcTemplate(ds);
		
		List<Release> releases = new ArrayList<Release>();
		
		String sql = "SELECT * FROM media.release WHERE label_id = ?";
		
		releases = jt.query(sql, new Object[] { labelId }, new RowMapper<Release>() {
			public Release mapRow(ResultSet rs, int rowNum) throws SQLException {
				Release release = new Release();
				Format format = new Format();
				Master master = new Master();
				Artist artist = new Artist();
				
				release.setReleaseId(rs.getInt("release_id"));
				release.setMasterId(rs.getInt("master_id"));
				release.setReleaseDay(rs.getInt("release_day"));
				release.setReleaseMonth(rs.getInt("release_month"));
				release.setReleaseYear(rs.getInt("release_year"));
				release.setCatalogNo(rs.getString("catalog_no"));
				release.setLabelCode(rs.getString("label_code"));
				release.setBarcode(rs.getString("barcode"));
				release.setComment(rs.getString("comment"));
				
				MasterDao mDao = new MasterDao();
				master = mDao.getMasterById(rs.getInt("master_id"));
				release.setTitle(master.getTitle());
				release.setImageURL(master.getImageURL());
				
				FormatDao fDao = new FormatDao();
				format = fDao.getFormatById(rs.getInt("format_id"));
				release.setFormat(format);
				
				ArtistDao aDao = new ArtistDao();
				artist = aDao.getArtistById(master.getArtist().getArtistId());
				release.setArtist(artist);
				
				return release;
			}
			
		});
		
		return releases;
		
	}
	
}
