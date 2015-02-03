package de.yellow.medienverwaltung.business;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import de.yellow.medienverwaltung.api.MasterDto;
import de.yellow.medienverwaltung.database.dao.MasterDao;
import de.yellow.medienverwaltung.database.entity.Master;
import de.yellow.medienverwaltung.database.util.MasterDtoConverter;

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

	@Transactional
	public long insertMaster(MasterDto master) {
		dao = new MasterDao();

		long masterId = dao.insertMaster(master);

		return masterId;
	}

	public MasterDto getMasterDtoById(long id) {
		dao = new MasterDao();

		Master master = dao.getMasterById(id);

		MasterDto masterDto = MasterDtoConverter.convertMaster(master);

		return masterDto;
	}

	public Master getMasterById(long id) {
		dao = new MasterDao();

		Master master = dao.getMasterById(id);

		return master;
	}
	
	public List<MasterDto> getMastersByArtistId(long artistId){
		dao = new MasterDao();
		
		List<MasterDto> masters = dao.getMastersByArtistId(artistId);
		
		return masters;
	}

}