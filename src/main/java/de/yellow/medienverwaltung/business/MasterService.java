package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.api.MasterDto;
import de.yellow.medienverwaltung.database.dao.MasterDao;
import de.yellow.medienverwaltung.database.entity.Master;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author marius
 *
 */
public class MasterService {

	private MasterDao dao;

	public List<Master> getAllMasters() {

		dao = new MasterDao();

		List<Master> masters = dao.getAllMasters();
		// TODO: Do something useful with data
		return masters;
	}
	
	public long insertMaster(MasterDto master) {
		dao = new MasterDao();
		
		long masterId = dao.insertMaster(master);
		
		return masterId;
	}
	
	public MasterDto getMasterById(long id) {
		dao = new MasterDao();
		
		MasterDto master = dao.getMasterById(id);
		
		return master;
	}

}