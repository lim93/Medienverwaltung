package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.database.dao.SubgenreDao;
import de.yellow.medienverwaltung.database.entity.Subgenre;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author marius
 *
 */
public class SubgenreService {

	private SubgenreDao dao;

	public List<Subgenre> getAllSubgenres() {

		dao = new SubgenreDao();

		List<Subgenre> subgenres = dao.getAllSubgenres();
		// TODO: Do something useful with data
		return subgenres;
	}

}