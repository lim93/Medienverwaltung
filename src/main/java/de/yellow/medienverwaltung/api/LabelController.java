package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.yellow.medienverwaltung.business.LabelService;
import de.yellow.medienverwaltung.database.entity.Label;

@Controller
public class LabelController {

	@RequestMapping(value = "api/labels/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Label> getLabel(@PathVariable("id") int id) {

		LabelService service = new LabelService();
		Label label = service.getLabelById(id);

		return new ResponseEntity<Label>(label, HttpStatus.OK);
	}

}
