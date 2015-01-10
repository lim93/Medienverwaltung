package de.yellow.medienverwaltung.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.yellow.medienverwaltung.business.FormatService;
import de.yellow.medienverwaltung.database.entity.Format;

@Controller
public class FormatController {

	@RequestMapping(value = "api/formats/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Format>> getFormats() {

		FormatService service = new FormatService();
		List<Format> formats = service.getAllFormats();

		return new ResponseEntity<List<Format>>(formats, HttpStatus.OK);

	}

}