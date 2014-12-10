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
import de.yellow.medienverwaltung.business.LabelService;
import de.yellow.medienverwaltung.business.TrackService;
import de.yellow.medienverwaltung.business.UserService;
import de.yellow.medienverwaltung.database.entity.Artist;
import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Label;
import de.yellow.medienverwaltung.database.entity.Track;
import de.yellow.medienverwaltung.database.entity.User;

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

	@RequestMapping(value = "api/tracks/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Track>> getTracks() {

		TrackService service = new TrackService();
		List<Track> tracks = service.getAllTracks();

		return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);

	}

	@RequestMapping(value = "api/genres/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Genre>> getGenres() {

		GenreService service = new GenreService();
		List<Genre> genres = service.getAllGenres();

		return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);

	}

	@RequestMapping(value = "api/artists/search/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Artist> getArtist(@RequestParam(value = "name") String name) {

		ArtistService service = new ArtistService();
		Artist artist = service.getArtistByName(name);

		return new ResponseEntity<Artist>(artist, HttpStatus.OK);
	}

	@RequestMapping(value = "api/artists/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Artist> getArtist(@PathVariable("id") int id) {

		ArtistService service = new ArtistService();
		Artist artist = service.getArtistById(id);

		return new ResponseEntity<Artist>(artist, HttpStatus.OK);
	}

	@RequestMapping(value = "api/labels/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Label> getLabel(@PathVariable("id") int id) {

		LabelService service = new LabelService();
		Label label = service.getLabelById(id);

		return new ResponseEntity<Label>(label, HttpStatus.OK);
	}

	@RequestMapping(value = "api/users/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {

		UserService service = new UserService();
		User user = service.getUserById(id);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "api/users/search/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUser(@RequestParam(value = "name") String name) {

		UserService service = new UserService();
		User user = service.getUserByName(name);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	// weitere Methoden für POST / PUT / DELETE...

}
