package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.database.entity.Track;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class TrackDao extends JdbcTemplate {
	
	/* Definition der Mapper-Klasse für Track-Objekte */
	private class TrackRowMapper implements RowMapper<Track> {
		public Track mapRow(ResultSet rs, int rowNum) throws SQLException {
			Track track = new Track();

			track.setTrackId(rs.getInt("track_id"));
			track.setNumber(rs.getString("number"));
			track.setTitle(rs.getString("title"));
			track.setDuration(rs.getString("duration").replaceFirst("00:", ""));

			return track;
		}
	}

	/* Logger */
	private static final Logger LOG = LoggerFactory.getLogger(TrackDao.class);

	/* Konstruktor */
	public TrackDao() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
		
		this.setDataSource(ds);
	}

	/* Liefert die Tracklist eines Releases */
	public List<Track> getTracklistByReleaseId(int releaseId) {

		String sql = "SELECT i.track_id, t.number, t.title, t.duration "
				+ "FROM tracklist_item i "
				+ "JOIN track t ON t.track_id = i.track_id "
				+ "WHERE release_id = ?";
		Object[] params = new Object[] { releaseId };
		
		List<Track> tracks = query(sql, params, new TrackRowMapper());
		
		return tracks;
	}

	/* Fügt einen Track in die Datenbank ein */
	public long insertTrack(Track track) {

		final String number = track.getNumber();
		final String title = track.getTitle();
		String duration = track.getDuration();
		
		// TODO: Prüfen, ob Track schon vorhanden ist. <- Release fehlt noch
//		String checkSql = "SELECT * FROM track "
//				+ "WHERE number = ? AND title = ? AND duration = ?";
//		Object[] params = new Object[] { number, title, duration };
//		
//		try {
//			Track checkTrack = queryForObject(checkSql, params, new TrackRowMapper());
//			if (checkTrack != null) {
//				throw new IllegalStateException("Der Track ist bereits vorhanden.");
//			}
//		} catch (EmptyResultDataAccessException e) {
//			// alles ok
//		}
		
		final String insertDuration = "00:" + duration;

		// Track speichern & ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String trackSql = "INSERT INTO track(number, title, duration) VALUES(?, ?, ?)";

		update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(trackSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, number);
				ps.setString(2, title);
				ps.setString(3, insertDuration);
				return ps;
			}
		}, keyHolder);

		long trackId = keyHolder.getKey().longValue();

		LOG.debug("Track wurde eingefügt mit der id: " + trackId);

		return trackId;
	}
	
}
