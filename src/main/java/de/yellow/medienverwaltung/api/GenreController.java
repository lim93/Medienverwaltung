package de.yellow.medienverwaltung.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.yellow.medienverwaltung.business.GenreService;
import de.yellow.medienverwaltung.database.entity.Genre;

@Controller
public class GenreController extends AbstractController {

    @RequestMapping(value = "api/genres/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Genre>> getGenres() {

        GenreService service = new GenreService();
        List<Genre> genres = service.getAllGenres();

        return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);

    }

}
