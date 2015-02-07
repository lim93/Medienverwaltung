package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.api.ReleaseDto;
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

	public long insertRelease(ReleaseDto release) {
		dao = new ReleaseDao();

		long releaseId = dao.insertRelease(release);

		return releaseId;
	}

	public Release getReleaseById(int id) {

		dao = new ReleaseDao();

		Release release = dao.getReleaseById(id);

		return release;
	}
	
	public List<Release> getReleasesByLabelId(long labelId) {
		
		dao = new ReleaseDao();
		
		List<Release> releases = dao.getReleasesByLabelId(labelId);
		
		return releases;
	}

}