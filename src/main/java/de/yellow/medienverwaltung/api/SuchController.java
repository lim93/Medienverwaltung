package de.yellow.medienverwaltung.api;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.yellow.medienverwaltung.business.Suchservice;

@Controller
public class SuchController extends AbstractController {

	@RequestMapping(value = "api/search/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<MasterDto>> search(
			@RequestParam(value = "suche") String suche) {

		Suchservice service = new Suchservice();
		Collection<MasterDto> searchresult = service.search(suche);

		return new ResponseEntity<Collection<MasterDto>>(searchresult,
				HttpStatus.OK);

	}

}
