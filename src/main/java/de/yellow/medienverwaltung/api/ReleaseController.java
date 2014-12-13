package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.yellow.medienverwaltung.business.ReleaseService;
import de.yellow.medienverwaltung.database.entity.Release;

@Controller
public class ReleaseController {

	@RequestMapping(value = "api/releases/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Release> getRelease(@PathVariable("id") int id) {
		
		//Rückgabe: Release-Entity mit Liste von Tracks und Master-ID
		ReleaseService service = new ReleaseService();
		Release release = service.getReleaseById(id);
		
		return new ResponseEntity<Release>(release, HttpStatus.OK);
	}
	
}
