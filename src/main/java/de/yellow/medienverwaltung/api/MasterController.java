package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MasterController {

	@RequestMapping(value = "api/master/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<MasterDto> getMaster(@PathVariable("id") int id) {

		// TODO: LUKAS: Master aus DB besorgen

		MasterDto master = new MasterDto();
		master.setMasterId(id);
		master.setArtist("Farin Urlaub Racing Team");
		master.setArtistId(1);
		master.setTitle("Faszination Weltraum");
		master.setGenre("Rock");
		master.setGenreId(1);
		master.setUrl("cover/0000.jpeg");
		master.setReleaseDate("2014");

		return new ResponseEntity<MasterDto>(master, HttpStatus.OK);
	}

	@RequestMapping(value = "api/master/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Integer> postNewMaster(@RequestBody MasterDto master) {

		System.out.println(master.toString());
		// TODO: Gruppe: Service aufrufen, um Master zu speichern
		int id = 5;

		return new ResponseEntity<Integer>(id, HttpStatus.OK);

	}

}
