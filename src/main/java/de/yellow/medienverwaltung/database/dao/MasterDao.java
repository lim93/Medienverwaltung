package de.yellow.medienverwaltung.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import de.yellow.medienverwaltung.api.MasterDto;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Master;
import de.yellow.medienverwaltung.database.entity.Release;
import de.yellow.medienverwaltung.database.entity.Subgenre;
import de.yellow.medienverwaltung.database.util.ConnectionFactory;

public class MasterDao {

    private static final Logger LOG = LoggerFactory.getLogger(MasterDao.class);

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
            throw new IllegalStateException("Es konnte keine DataSource erstellt werden");
        }
    }

    /**
     * Holt eine DataSource und erstellt daraus ein {@link JdbcTemplate}. Damit wird eine Abfrage gegen die Datenbank gestartet.
     * Das Ergebnis wird mit einem {@link RowMapper} auf die Klasse {@link Master} gemappt.
     * 
     * @return
     */
    public List<Master> getAllMasters() {

        DataSource dataSource = getDataSource();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Master> list = jdbcTemplate.query("select * from master", new RowMapper<Master>() {
            public Master mapRow(ResultSet rs, int rowNum) throws SQLException {
                Master master = new Master();

                master.setMasterId(rs.getInt("master_id"));
                // master.setArtistId(rs.getInt("artist_id"));
                master.setTitle(rs.getString("title"));
                master.setReleaseDay(rs.getInt("release_day"));
                master.setReleaseMonth(rs.getInt("release_month"));
                master.setReleaseYear(rs.getInt("release_year"));
                master.setImageURL(rs.getString("image_url"));
                master.setImage(rs.getBlob("image_data"));
                // master.setGenreId(rs.getInt("genre_id"));

                return master;
            }
        });

        return list;
    }

    public long insertMaster(MasterDto master) {

        DataSource dataSource = getDataSource();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Artist prüfen: Ist der angegebene Name bekannt?
        ArtistDao aDao = new ArtistDao();
        final Artist artist = aDao.getArtistByName(master.getArtist());

        if ((artist == null) || (artist.getArtistId() == 0)) {
            throw new IllegalArgumentException("Dieser Künstler ist uns nicht bekannt.");
        }

        final String title = master.getTitle();
        final String imageUrl = master.getUrl();
        final int genreId = master.getGenreId();

        // Prüfen, ob Master schon vorhanden (Titel und Artist)
        String titleSql = "SELECT master_id FROM master WHERE title = ? and artist_id = ?";

        List<Integer> list = jdbcTemplate.query(titleSql, new Object[] { title, artist.getArtistId() }, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

                Integer id = rs.getInt("master_id");

                return id;
            }
        });

        if (list.size() != 0) {
            throw new IllegalArgumentException("Master zu dieser Ver&ouml;ffentlichung ist bereits vorhanden.");
        }

        // TODO: Zusätzliche Validierung des Datums im Backend
        final Integer day = master.getReleaseDay();
        final Integer month = master.getReleaseMonth();
        final Integer year = master.getReleaseYear();

        // Master speichern & ID merken
        KeyHolder keyHolder = new GeneratedKeyHolder();

        final String masterSql = "INSERT INTO master(master_id, artist_id, title, release_day, release_month, release_year, image_url, genre_id) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(masterSql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, artist.getArtistId());
                ps.setString(2, title);
                ps.setInt(3, day);
                ps.setInt(4, month);
                ps.setInt(5, year);
                ps.setString(6, imageUrl);
                ps.setInt(7, genreId);
                return ps;
            }
        }, keyHolder);

        long masterId = keyHolder.getKey().longValue();

		// Subgenres speichern
		if (master.getSubgenreIds() != null
				&& master.getSubgenreIds().size() != 0) {
			final String subgenreSql = "INSERT INTO master_subgenre(master_id, subgenre_id) VALUES(?, ?)";

			for (int subgenreId : master.getSubgenreIds()) {

				Object[] params = new Object[] { masterId, subgenreId };

				jdbcTemplate.update(subgenreSql, params);

			}
		}

        LOG.debug("Master wurde eingefügt mit der id: " + masterId);

        return masterId;

    }

    public Master getMasterById(long id) {

        DataSource dataSource = getDataSource();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT master_id, artist_id, title, release_day, release_month, release_year, image_url, genre_id FROM master WHERE master_id = ?";

        List<Master> masterList = jdbcTemplate.query(sql, new Object[] { id }, new RowMapper<Master>() {
            public Master mapRow(ResultSet rs, int rowNum) throws SQLException {
                Master master = new Master();
                Artist artist = new Artist();
                Genre genre = new Genre();
                List<Subgenre> subgenres = new ArrayList<Subgenre>();
                List<Release> releases = new ArrayList<Release>();

                master.setMasterId(rs.getInt("master_id"));
                master.setTitle(rs.getString("title"));
                master.setReleaseDay(rs.getInt("release_day"));
                master.setReleaseMonth(rs.getInt("release_month"));
                master.setReleaseYear(rs.getInt("release_year"));
                master.setImageURL(rs.getString("image_url"));

                ArtistDao aDao = new ArtistDao();
                artist = aDao.getArtistById(rs.getInt("artist_id"));
                master.setArtist(artist);

                GenreDao gDao = new GenreDao();
                genre = gDao.getGenreById(rs.getInt("genre_id"));
                master.setGenre(genre);

                SubgenreDao sDao = new SubgenreDao();
                subgenres = sDao.getSubgenresByMasterId(master.getMasterId());
                master.setSubgenres(subgenres);

                ReleaseDao rDao = new ReleaseDao();
                releases = rDao.getReleasesByMasterId(master.getMasterId());
                master.setReleases(releases);

                return master;
            }
        });

		return masterList.get(0);
	}

	public List<Master> getMasterByArtistName(String name) {

		DataSource dataSource = getDataSource();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Master> masterList = new ArrayList<Master>();

		// Artist prüfen: Ist der angegebene Name bekannt?
		ArtistDao aDao = new ArtistDao();
		final Artist artist = aDao.getArtistByName(name);

		// Nichts gefunden: Return leere Liste
		if ((artist == null) || (artist.getArtistId() == 0)) {
			return masterList;
		}

		String sql = "SELECT master_id, artist_id, title, release_day, release_month, release_year, image_url, genre_id FROM master WHERE artist_id = ?";

		masterList = jdbcTemplate.query(sql,
				new Object[] { artist.getArtistId() }, new RowMapper<Master>() {
			public Master mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Master master = new Master();
				Artist artist = new Artist();
				Genre genre = new Genre();
				List<Subgenre> subgenres = new ArrayList<Subgenre>();
				List<Release> releases = new ArrayList<Release>();

				master.setMasterId(rs.getInt("master_id"));
				master.setTitle(rs.getString("title"));
				master.setReleaseDay(rs.getInt("release_day"));
				master.setReleaseMonth(rs.getInt("release_month"));
				master.setReleaseYear(rs.getInt("release_year"));
				master.setImageURL(rs.getString("image_url"));

				ArtistDao aDao = new ArtistDao();
				artist = aDao.getArtistById(rs.getInt("artist_id"));
				master.setArtist(artist);

				GenreDao gDao = new GenreDao();
				genre = gDao.getGenreById(rs.getInt("genre_id"));
				master.setGenre(genre);

				SubgenreDao sDao = new SubgenreDao();
				subgenres = sDao.getSubgenresByMasterId(master
						.getMasterId());
				master.setSubgenres(subgenres);

				ReleaseDao rDao = new ReleaseDao();
				releases = rDao.getReleasesByMasterId(master
						.getMasterId());
				master.setReleases(releases);

				return master;
			}
		});

		return masterList;
	}

}
