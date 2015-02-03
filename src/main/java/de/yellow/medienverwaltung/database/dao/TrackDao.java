package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.database.entity.Track;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class TrackDao {

	private DataSource ds;

	public TrackDao() {
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
	 * einem {@link RowMapper} auf die Klasse {@link Track} gemappt.
	 * 
	 * @return
	 */
	public List<Track> getAllTracks() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		List<Track> list = jdbcTemplate.query("select * from track",
				new RowMapper<Track>() {
			public Track mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Track track = new Track();

				track.setTrackId(rs.getInt("track_id"));
				track.setNumber(rs.getString("number"));
				track.setTitle(rs.getString("title"));
				track.setDuration(rs.getString("duration"));

				return track;
			}
		});

		return list;
	}

	public List<Track> getTracklistByReleaseId(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "SELECT i.track_id, t.number, t.title, t.duration FROM tracklist_item i JOIN track t on t.track_id = i.track_id WHERE release_id = ?";

		List<Track> tracks = jdbcTemplate.query(sql, new Object[] { id },
				new RowMapper<Track>() {
			public Track mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Track track = new Track();

				track.setTrackId(rs.getInt("track_id"));
				track.setNumber(rs.getString("number"));
				track.setTitle(rs.getString("title"));
				track.setDuration(rs.getString("duration"));

				return track;
			}
		});

		return tracks;

	}

	public long insertTrack(Track track) {

		// TODO: Prüfen, ob schon existiert!

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		final String number = track.getNumber();
		final String title = track.getTitle();
		final String duration = track.getDuration();

		// Track speichern & ID merken
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String trackSql = "INSERT INTO track(number, title, duration) VALUES(?, ?, ?)";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(trackSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, number);
				ps.setString(2, title);
				ps.setString(3, duration);
				return ps;
			}
		}, keyHolder);

		long trackId = keyHolder.getKey().longValue();

		System.out.println("Track wurde eingefügt mit der id: " + trackId);

		return trackId;

	}
}
