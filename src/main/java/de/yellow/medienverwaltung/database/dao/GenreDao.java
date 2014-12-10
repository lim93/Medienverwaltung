package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class GenreDao {

	private ConnectionFactory factory;

	/**
	 * Holt eine DataSource und erstellt daraus ein {@link JdbcTemplate}. Damit
	 * wird eine Abfrage gegen die Datenbank gestartet. Das Ergebnis wird mit
	 * einem {@link ResultSetExtractor} (Für Maps!) auf die Klasse {@link Genre}
	 * gemappt.
	 * 
	 * @return
	 */
	public Map<Integer, Genre> getAllGenres() {

		factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		Map<Integer, Genre> map = jdbcTemplate
				.query("SELECT g.genre_id, g.name as genre, s.subgenre_id, s.name as subgenre FROM genre g LEFT JOIN subgenre s on g.genre_id = s.genre_id;",
						new ResultSetExtractor<Map<Integer, Genre>>() {
					public Map<Integer, Genre> extractData(ResultSet rs)
							throws SQLException {
						Map<Integer, Genre> map = new HashMap<Integer, Genre>();

						while (rs.next()) {
							int id = rs.getInt("genre_id");

							if (!map.containsKey(id)) {
								Genre genre = new Genre();

								genre.setGenreId(id);
								genre.setName(rs.getString("genre"));

								List<Subgenre> subgenres = new ArrayList<Subgenre>();
								genre.setSubgenres(subgenres);

								map.put(id, genre);
							}

							if (rs.getInt("subgenre_id") != 0) {
								Subgenre subgenre = new Subgenre();

								subgenre.setSubgenreId(rs
										.getInt("subgenre_id"));
								subgenre.setGenreId(id);
								subgenre.setName(rs
										.getString("subgenre"));

								map.get(id).getSubgenres()
								.add(subgenre);
							}
						}

						return map;
					}
				});

		return map;

	}

}
