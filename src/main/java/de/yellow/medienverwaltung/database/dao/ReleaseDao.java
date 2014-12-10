package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Release;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class ReleaseDao {

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
	 * einem {@link RowMapper} auf die Klasse {@link Release} gemappt.
	 * 
	 * @return
	 */
	public List<Release> getAllReleases() {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Release> list = jdbcTemplate.query("select * from release",
				new RowMapper<Release>() {
			public Release mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Release release = new Release();

				release.setReleaseId(rs.getInt("release_id"));
				release.setMasterId(rs.getInt("master_id"));
				release.setLabelId(rs.getInt("label_id"));
				release.setFormatId(rs.getInt("format_id"));
				release.setReleased(rs.getDate("released"));
				release.setCatalog(rs.getString("cat_no"));
				release.setLabelCode(rs.getString("lc"));
				release.setBarcode(rs.getString("barcode"));
				release.setComment(rs.getString("comment"));
				
				return release;
			}
		});

		return list;
	}
}
