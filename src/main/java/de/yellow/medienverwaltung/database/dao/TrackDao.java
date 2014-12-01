package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Track;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class TrackDao {

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
	 * einem {@link RowMapper} auf die Klasse {@link Track} gemappt.
	 * 
	 * @return
	 */
	public List<Track> getAllTracks() {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Track> list = jdbcTemplate.query("select * from track",
				new RowMapper<Track>() {
			public Track mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Track track = new Track();

				track.setTrackID(rs.getInt("track_id"));
				track.setNumber(rs.getInt("number"));
				track.setTitle(rs.getString("title"));
				track.setDuration(rs.getString("duration"));

				return track;
			}
		});

		return list;
	}
}
