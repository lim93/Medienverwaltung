package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.database.dao.ReleaseDao;
import de.yellow.medienverwaltung.database.entity.Release;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author marius
 *
 */
public class ReleaseService {

	private ReleaseDao dao;

	public List<Release> getAllReleases() {

		dao = new ReleaseDao();

		List<Release> releases = dao.getAllReleases();
		// TODO: Do something useful with data
		return releases;
	}

}