package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.database.dao.LabelDao;
import de.yellow.medienverwaltung.database.entity.Label;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author marius
 *
 */
public class LabelService {

	private LabelDao dao;

	public List<Label> getAllLabels() {

		dao = new LabelDao();

		List<Label> labels = dao.getAllLabels();
		// TODO: Do something useful with data
		return labels;
	}

}
