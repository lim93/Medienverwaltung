package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Format;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class FormatDao extends JdbcTemplate {

	/* Definition der Mapper-Klasse für Format-Objekte */
	private class FormatRowMapper implements RowMapper<Format> {
		public Format mapRow(ResultSet rs, int rowNum) throws SQLException {
			Format format = new Format();

			format.setFormatId(rs.getInt("format_id"));
			format.setType(rs.getString("type"));

			return format;
		}
	}
	
	/* Konstruktor */
	public FormatDao() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
		
		this.setDataSource(ds);
	}

	/* Liefert Liste aller Formate */
	public List<Format> getAllFormats() {

		String sql = "SELECT * FROM format";
		
		List<Format> formats = query(sql, new FormatRowMapper());
		
		return formats;
	}

	/* Liefert ein Format anhand der Format-ID */
	public Format getFormatById(int id) {

		Format format = new Format();
		
		String sql = "SELECT * FROM format WHERE format_id = ?";
		Object[] params = new Object[] { id };

		format = queryForObject(sql, params, new FormatRowMapper());

		return format;
	}
	
}
