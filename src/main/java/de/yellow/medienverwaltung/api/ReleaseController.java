package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.yellow.medienverwaltung.business.ReleaseService;
import de.yellow.medienverwaltung.database.entity.Release;

@Controller
public class ReleaseController {

	@RequestMapping(value = "api/releases/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Release> getRelease(@PathVariable("id") int id) {

		// Rückgabe: Release-Entity mit Liste von Tracks und Master-ID
		ReleaseService service = new ReleaseService();
		Release release = service.getReleaseById(id);

		return new ResponseEntity<Release>(release, HttpStatus.OK);
	}

	@RequestMapping(value = "api/release/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Long> postNewRelease(@RequestBody ReleaseDto release) {

		System.out.println(release.toString());
		ReleaseService service = new ReleaseService();
		long id = service.insertRelease(release);

		return new ResponseEntity<Long>(id, HttpStatus.OK);

	}

}
