package de.yellow.medienverwaltung.business;

import java.util.List;

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

	public List<Artist> getAllArtists() {

		dao = new ArtistDao();

		List<Artist> artists = dao.getAllArtists();
		// TODO: Do something useful with data
		return artists;
	}

}