package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MasterController {

	@RequestMapping(value = "api/master/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Integer> postNewMaster(@RequestBody MasterDto master) {

		System.out.println(master.toString());
		// TODO: Gruppe: Service aufrufen, um Master zu speichern
		int id = 5;

		return new ResponseEntity<Integer>(id, HttpStatus.OK);

	}

}
