package de.yellow.medienverwaltung.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SuchController extends AbstractController {

    @RequestMapping(value = "api/search/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<MasterDto>> search(@RequestParam(value = "suche") String suche) {

        List<MasterDto> returnList = new ArrayList<MasterDto>();

        MasterDto m1 = new MasterDto();
        m1.setArtist("Rage Against The Machine");
        m1.setArtistId(1);
        m1.setTitle("Rage Against The Machine");
        m1.setGenre("Metal");
        m1.setGenreId(2);
        m1.setUrl("cover/0001.jpeg");
        m1.setReleaseDay(0);
        m1.setReleaseMonth(0);
        m1.setReleaseYear(1992);
        returnList.add(m1);

        return new ResponseEntity<List<MasterDto>>(returnList, HttpStatus.OK);

    }

}
