package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.api.LabelDto;
import de.yellow.medienverwaltung.database.entity.Label;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class LabelDao extends JdbcTemplate {
	
	/* Definition der Mapper-Klasse für Label-Objekte */
	private class LabelRowMapper implements RowMapper<Label> {
		public Label mapRow(ResultSet rs, int rowNum) throws SQLException {
			Label label = new Label();
			
			label.setLabelId(rs.getInt("label_id"));
			label.setName(rs.getString("name"));
			label.setWebsite(rs.getString("website"));
			
			return label;
		}
	}
	
	/* Logger */
	private static final Logger LOG = LoggerFactory.getLogger(LabelDao.class);

	/* Konstruktor */
	public LabelDao() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
		
		this.setDataSource(ds);
	}

	/* Liefert ein Label-Objekt anhand der Label-ID */
	public Label getLabelById(int labelId) {

		Label label = new Label();
		
		String sql = "SELECT * FROM label WHERE label_id = ?";
		Object[] params = new Object[] { labelId };
		
		label = queryForObject(sql, params, new LabelRowMapper());

		return label;
	}

	/* Liefert ein Label-Objekt anhand des Namens.
	 * Wirft eine Exception, falls das Label noch nicht vorhanden ist. */
	public Label getLabelByName(String name) {

		String sql = "SELECT * FROM label WHERE name = ?";
		Object[] params = new Object[] { name };

		try {
			Label label = queryForObject(sql, params, new LabelRowMapper());
			return label;
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException(
					"Dieses Label ist uns nicht bekannt.");
		}
	}

	/* Fügt ein Label der Datenbank hinzu. */
	public long insert(LabelDto label) {

		final String name = label.getName();
		final String website = label.getWebsite();

		// Prüfen, ob schon existent
		try {
			Label controlLabel = getLabelByName(name);

			if (controlLabel != null) {
				throw new IllegalStateException(
						"Dieses Label ist bereits bekannt.");
			}
		} catch (IllegalArgumentException e) {
			// alles ok
		}

		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String sql = "INSERT INTO label(label_id, name, website) VALUES(NULL, ?, ?)";

		update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setString(2, website);
				return ps;
			}
		}, keyHolder);

		long labelId = keyHolder.getKey().longValue();

		LOG.debug("Label wurde eingefügt mit der id: " + labelId);

		return labelId;
	}

}
