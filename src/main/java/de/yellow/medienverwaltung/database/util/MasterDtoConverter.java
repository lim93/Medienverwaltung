package de.yellow.medienverwaltung.database.util;

import java.util.ArrayList;
import java.util.List;

import de.yellow.medienverwaltung.api.MasterDto;
import de.yellow.medienverwaltung.database.entity.Master;
import de.yellow.medienverwaltung.database.entity.Subgenre;

public class MasterDtoConverter {

	public static MasterDto convertMaster(Master master) {

		MasterDto dto = new MasterDto();

		dto.setMasterId(master.getMasterId());
		dto.setArtist(master.getArtist().getName());
		dto.setArtistId(master.getArtist().getArtistId());
		dto.setTitle(master.getTitle());
		dto.setGenreId(master.getGenre().getGenreId());
		dto.setGenre(master.getGenre().getName());

		List<Integer> subgenreList = new ArrayList<Integer>();

		for (Subgenre subgenre : master.getSubgenres()) {
			subgenreList.add(subgenre.getSubgenreId());
		}

		dto.setSubgenreIds(subgenreList);

		dto.setUrl(master.getImageURL());
		dto.setReleaseDay(master.getReleaseDay());
		dto.setReleaseMonth(master.getReleaseMonth());
		dto.setReleaseYear(master.getReleaseYear());

		return dto;

	}

}
