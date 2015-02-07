package de.yellow.medienverwaltung.business;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import de.yellow.medienverwaltung.api.ArtistDto;
import de.yellow.medienverwaltung.database.dao.ArtistDao;
import de.yellow.medienverwaltung.database.entity.Artist;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author marius
 *
 */
public class ArtistService {

	private ArtistDao dao;

	public Artist getArtistByName(String name) {

		dao = new ArtistDao();

		Artist artist = dao.getArtistByName(name);

		return artist;
	}

	public ArtistDto getArtistDtoById(int id) {

		dao = new ArtistDao();

		ArtistDto artist = dao.getArtistDtoById(id);

		return artist;
	}

	@Transactional
	public long insertArtist(ArtistDto artist) {

		dao = new ArtistDao();

		long artistId = dao.insert(artist);

		return artistId;
	}

	public List<Artist> getArtistsByLabelId(long labelId) {

		dao = new ArtistDao();

		List<Artist> artists = dao.getArtistsByLabelId(labelId);

		return artists;
	}
	
}
