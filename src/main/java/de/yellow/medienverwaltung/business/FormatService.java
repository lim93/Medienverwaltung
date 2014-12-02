package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.database.dao.FormatDao;
import de.yellow.medienverwaltung.database.entity.Format;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author marius
 *
 */
public class FormatService {

	private FormatDao dao;

	public List<Format> getAllFormats() {

		dao = new FormatDao();

		List<Format> formats = dao.getAllFormats();
		// TODO: Do something useful with data
		return formats;
	}

}
