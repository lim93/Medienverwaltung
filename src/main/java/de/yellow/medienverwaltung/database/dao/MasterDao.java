package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.api.MasterDto;
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
	
	public int insertMaster(MasterDto master) {
		
		DataSource dataSource = getDataSource();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Timestamp date = createTimestampDDMMYYYY(master.getReleaseDate());
		
		int artistId = 1;
		
		String sql = "INSERT INTO master(artist_id, title, released, image_url) VALUES(?, ?, ?, ?)";
		
		Object[] params = new Object[] {1, master.getTitle(), date, master.getUrl()};
		
		int row = jdbcTemplate.update(sql, params);
		
		System.out.println(row);
		
		return row;
		
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
