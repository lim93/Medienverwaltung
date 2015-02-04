package de.yellow.medienverwaltung.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.yellow.medienverwaltung.business.ArtistService;
import de.yellow.medienverwaltung.business.ReleaseService;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Release;

@Controller
public class ArtistController extends AbstractController {

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

		LOG.debug("Artist gefunden: " + artist.getName());

		return new ResponseEntity<Artist>(artist, HttpStatus.OK);
	}

	@RequestMapping(value = "api/artists/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Long> postNewArtist(@RequestBody ArtistDto artist) {

		LOG.debug(artist.toString());

		ArtistService service = new ArtistService();
		
		long artistId = service.insertArtist(artist);

		return new ResponseEntity<Long>(artistId, HttpStatus.OK);
	}
	
    @RequestMapping(value = "api/artists/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Artist>> getArtistsByLabelId(@RequestParam(value = "labelId") long labelId) {
    	
    	ArtistService service = new ArtistService();
    	
    	List<Artist> artists = service.getArtistsByLabelId(labelId);
    	
    	return new ResponseEntity<List<Artist>>(artists, HttpStatus.OK);
    }

}
