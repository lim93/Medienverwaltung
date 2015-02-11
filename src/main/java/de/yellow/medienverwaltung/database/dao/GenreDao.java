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
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class GenreDao extends JdbcTemplate {
	
	/* Definition der Mapper-Klasse für Genre-Objekte */
	private class GenreRowMapper implements RowMapper<Genre> {
		public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
			Genre genre = new Genre();

			genre.setGenreId(rs.getInt("genre_id"));
			genre.setName(rs.getString("name"));

			return genre;
		}
	}
	
	/* Konstruktor */
	public GenreDao() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
		
		this.setDataSource(ds);
	}

	/* Liefert die Liste aller Genres inkl. der zugehörigen Subgenres*/
	public Map<Integer, Genre> getAllGenres() {

		String sql = "SELECT g.genre_id, g.name as genre, s.subgenre_id, s.name as subgenre FROM genre g LEFT JOIN subgenre s on g.genre_id = s.genre_id";
		
		Map<Integer, Genre> map = query(sql, 
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

	/* Liefert ein Genre-Objekt anhand der Genre-ID */
	public Genre getGenreById(int genreId) {

		Genre genre = new Genre();
		
		String sql = "SELECT * FROM genre WHERE genre_id = ?";
		Object[] params = new Object[] { genreId };

		genre = queryForObject(sql, params, new GenreRowMapper());

		return genre;
	}

	/* Liefert das Genre eines Masters */
	public Genre getGenreByMasterId(int masterId) {

		Genre genre = new Genre();
		
		String sql = "SELECT g.genre_id, g.name FROM genre g "
				+ "JOIN master m ON m.genre_id = g.genre_id WHERE m.master_id = ?";
		Object[] params = new Object[] { masterId };

		genre = queryForObject(sql, params, new GenreRowMapper());

		return genre;
	}

	/* Liefert eine Liste von Genres, die einem bestimmten Artist zugeordnet sind */
	public List<Genre> getGenresByArtistId(int artistId) {

		String sql = "SELECT DISTINCT g.genre_id, g.name FROM genre g "
				+ "JOIN master m ON m.genre_id = g.genre_id WHERE m.artist_id = ?";
		Object[] params = new Object[] { artistId };
		
		List<Genre> genres = query(sql, params, new GenreRowMapper());
		
		return genres;
	}

}
