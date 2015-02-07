package de.yellow.medienverwaltung.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.yellow.medienverwaltung.api.MasterDto;
import de.yellow.medienverwaltung.database.dao.MasterDao;
import de.yellow.medienverwaltung.database.entity.Master;
import de.yellow.medienverwaltung.database.util.MasterDtoConverter;

public class Suchservice {

	private static final Logger LOG = LoggerFactory
			.getLogger(Suchservice.class);

	public Collection<MasterDto> search(String suche) {

		MasterDao mDao = new MasterDao();
		Map<Integer, MasterDto> returnMap = new HashMap<Integer, MasterDto>();

		// Suche über Artist
		List<Master> byArtistList = mDao.getMastersByArtistName(suche);

		for (Master master : byArtistList) {
			returnMap.put(master.getMasterId(),
					MasterDtoConverter.convertMaster(master));
		}

		// Suche über Release (Name)
		List<Master> byTitle = mDao.getMastersByTitle(suche);

		for (Master master : byTitle) {
			returnMap.put(master.getMasterId(),
					MasterDtoConverter.convertMaster(master));
		}

		// Suche über Label
		// optional

		// Suche über LC / CAT / Barcode...
		// optional

		LOG.debug("Suchergebnisse für " + suche + ": " + returnMap.size());

		return returnMap.values();

	}

}
