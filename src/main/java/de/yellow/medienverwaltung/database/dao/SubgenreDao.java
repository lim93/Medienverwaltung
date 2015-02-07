package de.yellow.medienverwaltung.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class SubgenreDao extends JdbcTemplate {
	
	/* Definition der Mapper-Klasse für Artist-Objekte */
	private class SubgenreRowMapper implements RowMapper<Subgenre> {
		public Subgenre mapRow(ResultSet rs, int rowNum) throws SQLException {
			Subgenre subgenre = new Subgenre();

			subgenre.setSubgenreId(rs.getInt("subgenre_id"));
			subgenre.setGenreId(rs.getInt("genre_id"));
			subgenre.setName(rs.getString("name"));

			return subgenre;
		}
	}

	/* Konstruktor */
	public SubgenreDao() {
		ConnectionFactory factory = new ConnectionFactory();

		DataSource ds = factory.getDataSource();

		if (ds == null) {
			throw new IllegalStateException(
					"Es konnte keine DataSource erstellt werden");
		}
		
		this.setDataSource(ds);
	}

	/* Liefert die Liste der Subgenres eines Masters */
	public List<Subgenre> getSubgenresByMasterId(int masterId) {

		String sql = "SELECT m.subgenre_id, s.genre_id, s.name "
				+ "FROM master_subgenre m "
				+ "JOIN subgenre s ON m.subgenre_id = s.subgenre_id "
				+ "WHERE master_id = ?";
		Object[] params = new Object[] { masterId };

		List<Subgenre> subgenres = query(sql, params, new SubgenreRowMapper());
		
		return subgenres;
	}

	/* Liefert eine Liste der Subgenres, die dem Artist mit der übergebenen ID zugeordnet wurden */
	public List<Subgenre> getSubgenresByArtistId(int artistId) {

		String sql = "SELECT distinct ms.subgenre_id, s.genre_id, s.name FROM master_subgenre ms "
				+ "JOIN subgenre s on ms.subgenre_id = s.subgenre_id "
				+ "JOIN master m on ms.master_id = m.master_id "
				+ "WHERE m.artist_id = ?";
		Object[] params = new Object[] { artistId };

		List<Subgenre> subgenres = query(sql, params, new SubgenreRowMapper());
		
		return subgenres;
	}

	/* Liefert die am häufigsten vorkommenden Subgenres in der Sammlung eines Users */
	public Map<Integer, Subgenre> getTopSubgenresByUserId(int userId, int limit) {

		Map<Integer, Subgenre> subgenres = new HashMap<Integer, Subgenre>();
		
		String sql = "SELECT s.subgenre_id, s.genre_id, s.name from subgenre s "
				+ "JOIN master_subgenre ms on s.subgenre_id = ms.subgenre_id "
				+ "JOIN master m on ms.master_id= m.master_id "
				+ "JOIN media.release r on m.master_id = r.master_id "
				+ "JOIN collection_item c on r.release_id = c.release_id "
				+ "WHERE c.user_id = ? "
				+ "GROUP by s.subgenre_id "
				+ "ORDER by COUNT(s.subgenre_id) desc " + "LIMIT ?";
		Object[] params = new Object[] { userId, limit };

		List<Subgenre> subgenreList = query(sql, params, new SubgenreRowMapper());

		int counter = 0;
		for (Subgenre subgenre : subgenreList) {
			counter++;
			subgenres.put(counter, subgenre);
		}

		return subgenres;
	}
	
}
