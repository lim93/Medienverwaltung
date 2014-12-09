package de.yellow.medienverwaltung.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.yellow.medienverwaltung.business.ArtistService;
import de.yellow.medienverwaltung.business.GenreService;
import de.yellow.medienverwaltung.business.TrackService;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Track;


//Annotation für Controller-Klassen
@Controller
public class MyRestController {

	// Mapping auf /medienverwaltung/api/dto, Rückgabe im JSON-Format. Es kann
	// ein optionaler Parameter "input" mitgegeben werden (?input=blub)
	@RequestMapping(value = "api/dto", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<DataTransferObject> getDto(
			@RequestParam(value = "input", required = false) String input) {

		String output = "";

		if (input != null) {
			output = input;
		}

		// ResponseEntity mit Nutzlast und HTTP-Status 200 OK. Das
		// "DataTransferObject" ist ein simples POJO mit ein paar Gettern
		// und Settern + Standardkonstruktor
		return new ResponseEntity<DataTransferObject>(new DataTransferObject(
				10, output), HttpStatus.OK);

	}

	@RequestMapping(value = "api/track/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Track>> getTracks() {

		TrackService service = new TrackService();
		List<Track> tracks = service.getAllTracks();

		return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);

	}

	@RequestMapping(value = "api/genre/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Genre>> getGenres() {

		GenreService service = new GenreService();
		List<Genre> genres = service.getAllGenres();

		return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);

	}
	
	@RequestMapping(value = "api/artist/{name}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Artist> getArtist(
			@PathVariable("name") String name) {
		
		ArtistService service = new ArtistService();
		Artist artist = service.getArtist(name);
		
		return new ResponseEntity<Artist>(artist, HttpStatus.OK);
	}
	
	// weitere Methoden für POST / PUT / DELETE...

}
