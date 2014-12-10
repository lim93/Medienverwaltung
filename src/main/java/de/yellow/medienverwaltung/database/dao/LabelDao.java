package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Label;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class LabelDao {

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

		label = (Label) jt.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Label>(Label.class));

		return label;
	}
}
