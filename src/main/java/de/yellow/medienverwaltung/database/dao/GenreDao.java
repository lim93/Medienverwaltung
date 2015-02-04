package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class GenreDao {

	private ConnectionFactory factory;

	private DataSource ds;

	public GenreDao() {
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
	 * einem {@link ResultSetExtractor} (Für Maps!) auf die Klasse {@link Genre}
	 * gemappt.
	 * 
	 * @return
	 */
	public Map<Integer, Genre> getAllGenres() {

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

	public Genre getGenreById(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select * from genre where genre_id = ?";

		Genre genre = new Genre();

		genre = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Genre>(Genre.class));

		return genre;
	}

	public Genre getGenreByMasterId(int masterId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select g.genre_id, g.name from genre g "
				+ "join master m on m.genre_id = g.genre_id where m.master_id = ?";

		Genre genre = new Genre();

		genre = jdbcTemplate.queryForObject(sql, new Object[] { masterId },
				new BeanPropertyRowMapper<Genre>(Genre.class));

		return genre;
	}

	public List<Genre> getGenresByArtistId(int artistId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = "select g.genre_id, g.name from genre g "
				+ "join master m on m.genre_id = g.genre_id where m.artist_id = ?";

		List<Genre> list = jdbcTemplate.query(sql, new Object[] { artistId },
				new RowMapper<Genre>() {
			public Genre mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Genre genre = new Genre();

				genre.setGenreId(rs.getInt("genre_id"));
				genre.setName(rs.getString("name"));

				return genre;
			}
		});

		return list;
	}

}
