package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.api.MasterDto;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Master;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class MasterDao {

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
	 * einem {@link RowMapper} auf die Klasse {@link Master} gemappt.
	 * 
	 * @return
	 */
	public List<Master> getAllMasters() {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Master> list = jdbcTemplate.query("select * from master",
				new RowMapper<Master>() {
			public Master mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Master master = new Master();

				master.setMasterId(rs.getInt("master_id"));
				master.setArtistId(rs.getInt("artist_id"));
				master.setTitle(rs.getString("title"));
				master.setReleased(rs.getDate("released"));
				master.setImageURL(rs.getString("image_url"));
				master.setImage(rs.getBlob("image_data"));

				return master;
			}
		});

		return list;
	}
	
	public long insertMaster(MasterDto master) {
		
		DataSource dataSource = getDataSource();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		// TODO: Artist überprüfen: Falls Artist schon vorhanden in DB -> vorhandene Artist-ID benutzen;
		// sonst -> zuerst Artist anlegen.
		final int artistId = 1;
		
		final String title = master.getTitle();
		final Timestamp releaseDate = createTimestampDDMMYYYY(master.getReleaseDate());
		final String imageUrl = master.getUrl();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		final String sql = "INSERT INTO master(master_id, artist_id, title, released, image_url) VALUES(NULL, ?, ?, ?, ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, artistId);
				ps.setString(2, title);
				ps.setTimestamp(3, releaseDate);
				ps.setString(4, imageUrl);
				return ps;
			}
		}, keyHolder);
			
		long masterId = keyHolder.getKey().longValue();
		
		System.out.println("Master wurde eingefügt mit der id: " + masterId);
		
		return masterId;
		
	}
	
	public MasterDto getMasterById(long id) {
		
//		Benötigt werden:
//		aus der Master-Tabelle:
//		int masterId; 		(nicht wirklich aus Tabelle, ist ja schon bekannt)
//		int artistId; 		(artist_id)
//		String title; 		(title)
//		String releaseDate; (released)
//		String url;   		(image_url)
		
//		aus der Artist-Tabelle:
//		String artist; (name)
		
//		aus der master_subgenre-Tabelle:
//		List<Integer> subgenreIds; (subgenre_id)
		
//		aus der Subgenre-Tabelle:
//		String genre; (name)
//		int genreId;  (genre_id)
		
		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		MasterDto masterDto = new MasterDto();
		
		String sql = "SELECT artist_id, title, released, image_url FROM master WHERE master_id = ?";
		
		Master master = new Master();
		
		master = (Master) jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Master>(Master.class));
		
		
		
		return masterDto;
	}
	
	/**
	* Erstellt aus einem String im Format "dd.MM.yyyy" einen Timestamp. Die
	* Stunden, Minuten etc. sind dabei 0.
	*/
	public static Timestamp createTimestampDDMMYYYY(String stringDate) {
		Timestamp timestamp = new Timestamp(0);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
		timestamp.setTime(df.parse(stringDate).getTime());
		} catch (ParseException parseException) {
		throw new IllegalArgumentException(stringDate
		+ " ist keine gültige Zeitangabe. Zeitangaben müssen im Format DD.MM.YYYY übergeben werden.", parseException);
		}
		return timestamp;
	}
}
