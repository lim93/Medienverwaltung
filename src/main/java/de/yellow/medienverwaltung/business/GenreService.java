package de.yellow.medienverwaltung.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

		List<Genre> returnList = new ArrayList<Genre>();

		dao = new GenreDao();

		Map<Integer, Genre> genres = dao.getAllGenres();

		for (Genre genre : genres.values()) {
			returnList.add(genre);
		}
		return returnList;
	}

}