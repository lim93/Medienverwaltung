package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.yellow.medienverwaltung.business.LabelService;
import de.yellow.medienverwaltung.database.entity.Label;

@Controller
public class LabelController extends AbstractController {

	@RequestMapping(value = "api/labels/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Label> getLabel(@PathVariable("id") int id) {

		LabelService service = new LabelService();
		Label label = service.getLabelById(id);

		return new ResponseEntity<Label>(label, HttpStatus.OK);
	}

	@RequestMapping(value = "api/labels/search/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Label> getLabel(
			@RequestParam(value = "name") String name) {

		Label label = null;

		// DEBUG
		// TODO:
		if (name.equals("Hot Action Records")) {
			label = new Label();
			label.setName("Hot Action records");
			label.setLabelId(2);
			label.setWebsite("http://www.bademeister.com/v11/kontakt/impressum.php");
			LOG.debug("Label gefunden: " + label.getName());
		} else {
			throw new IllegalArgumentException(
					"Das Label ist uns nicht bekannt");
		}

		return new ResponseEntity<Label>(label, HttpStatus.OK);
	}

	@RequestMapping(value = "api/labels/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Long> postNewLabel(@RequestBody Label label) {

		LOG.debug(label.toString());

		LabelService service = new LabelService();
		long labelId = new Long(42);

		return new ResponseEntity<Long>(labelId, HttpStatus.OK);

	}

}
