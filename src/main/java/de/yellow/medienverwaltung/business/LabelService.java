package de.yellow.medienverwaltung.business;

import de.yellow.medienverwaltung.api.LabelDto;
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
	
	public Label getLabelById(int id) {
		
		dao = new LabelDao();
		
		Label label = dao.getLabelById(id);
		
		return label;
	}
	
	public Label getLabelByName(String name) {
		
		dao = new LabelDao();
		
		Label label = dao.getLabelByName(name);
		
		return label;
	}
	
	public long insert(LabelDto label) {
		
		dao = new LabelDao();
		
		Long labelId = dao.insert(label);
		
		return labelId;
	}

}
