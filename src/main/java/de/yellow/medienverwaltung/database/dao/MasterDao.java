package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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

				master.setMasterID(rs.getInt("master_id"));
				master.setArtistID(rs.getInt("artist_id"));
				master.setTitle(rs.getString("title"));
				master.setReleased(rs.getDate("released"));
				master.setImageURL(rs.getString("image_url"));
				master.setImage(rs.getBlob("image_data"));

				return master;
			}
		});

		return list;
	}
}
