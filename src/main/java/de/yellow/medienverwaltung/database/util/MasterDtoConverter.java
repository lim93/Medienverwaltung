package de.yellow.medienverwaltung.database.util;

import de.yellow.medienverwaltung.api.MasterDto;
import de.yellow.medienverwaltung.database.entity.Master;

public class MasterDtoConverter {

	public static MasterDto convertMaster(Master master) {

		MasterDto dto = new MasterDto();

		dto.setMasterId(master.getMasterId());
		// TODO: Artist
		dto.setArtistId(master.getArtistId());
		dto.setTitle(master.getTitle());
		// TODO: genre
		dto.setGenreId(master.getGenreId());
		// TODO: subgenres
		dto.setUrl(master.getImageURL());
		dto.setReleaseDay(master.getReleaseDay());
		dto.setReleaseMonth(master.getReleaseMonth());
		dto.setReleaseYear(master.getReleaseYear());

		return dto;

	}

}
