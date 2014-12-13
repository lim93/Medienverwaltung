package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.yellow.medienverwaltung.business.ArtistService;
import de.yellow.medienverwaltung.database.entity.Artist;

@Controller
public class ArtistController {

	@RequestMapping(value = "api/artists/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Artist> getArtist(@PathVariable("id") int id) {

		ArtistService service = new ArtistService();
		Artist artist = service.getArtistById(id);

		return new ResponseEntity<Artist>(artist, HttpStatus.OK);
	}

	@RequestMapping(value = "api/artists/search/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Artist> getArtist(
			@RequestParam(value = "name") String name) {

		ArtistService service = new ArtistService();
		Artist artist = service.getArtistByName(name);

		return new ResponseEntity<Artist>(artist, HttpStatus.OK);
	}

}
