package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.database.dao.GenreDao;
import de.yellow.medienverwaltung.database.entity.Genre;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author marius
 *
 */
public class GenreService {

	private GenreDao dao;

	public List<Genre> getAllGenres() {

		dao = new GenreDao();

		List<Genre> genres = dao.getAllGenres();
		// TODO: Do something useful with data
		return genres;
	}

}