package de.yellow.medienverwaltung.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.yellow.medienverwaltung.business.TrackService;
import de.yellow.medienverwaltung.database.entity.Track;

@Controller
public class TrackController extends AbstractController {

    @RequestMapping(value = "api/tracks/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Track>> getTracks() {

        TrackService service = new TrackService();
        List<Track> tracks = service.getAllTracks();

        return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);

    }

}
