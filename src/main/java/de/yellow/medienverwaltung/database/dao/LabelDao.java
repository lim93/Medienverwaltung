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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.api.LabelDto;
import de.yellow.medienverwaltung.database.entity.Label;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class LabelDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(LabelDao.class);

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
	 * einem {@link RowMapper} auf die Klasse {@link Label} gemappt.
	 * 
	 * @return
	 */
	public List<Label> getAllLabels() {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Label> list = jdbcTemplate.query("select * from label",
				new RowMapper<Label>() {
			public Label mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Label label = new Label();

				label.setLabelId(rs.getInt("label_id"));
				label.setName(rs.getString("name"));
				label.setWebsite(rs.getString("website"));

				return label;
			}
		});

		return list;
	}

	public Label getLabelById(int id) {

		DataSource ds = getDataSource();

		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql = "select * from label where label_id = ?";

		Label label = new Label();

		label = jt.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Label>(Label.class));

		return label;
	}

	public Label getLabelByName(String name) {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from label where name = ?";

		try {
			Label label = jdbcTemplate.queryForObject(sql,
					new Object[] { name }, new BeanPropertyRowMapper<Label>(
							Label.class));

			return label;
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException(
					"Dieses Label ist uns nicht bekannt.");
		}
		
	}
	
	public long insert(LabelDto label) {
		
		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		final String name = label.getName();
		final String website = label.getWebsite();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		final String sql = "INSERT INTO label(label_id, name, website) VALUES(NULL, ?, ?)";

		jdbcTemplate.update(new PreparedStatementCreator() {
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
