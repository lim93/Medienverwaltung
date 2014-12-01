package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.database.dao.TrackDao;
import de.yellow.medienverwaltung.database.entity.Track;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author krispin
 *
 */
public class TrackService {

	private TrackDao dao;

	public List<Track> getAllTracks() {

		dao = new TrackDao();

		List<Track> tracks = dao.getAllTracks();
		// TODO: Do something useful with data
		return tracks;
	}

}
