package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Format;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class FormatDao {

	private DataSource ds;

	public FormatDao() {
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
	 * einem {@link RowMapper} auf die Klasse {@link Format} gemappt.
	 * 
	 * @return
	 */
	public List<Format> getAllFormats() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		List<Format> list = jdbcTemplate.query("select * from format",
				new RowMapper<Format>() {
			public Format mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Format format = new Format();

				format.setFormatId(rs.getInt("format_id"));
				format.setType(rs.getString("type"));

				return format;
			}
		});

		return list;
	}

	public Format getFormatById(int id) {

		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql = "select * from format where format_id = ?";

		Format format = new Format();

		format = jt.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Format>(Format.class));

		return format;
	}
}
