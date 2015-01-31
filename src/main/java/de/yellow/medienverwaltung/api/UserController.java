package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.yellow.medienverwaltung.business.UserService;
import de.yellow.medienverwaltung.database.entity.User;

@Controller
public class UserController extends AbstractController {

	@RequestMapping(value = "api/users/login/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Integer> validateLogin(@RequestBody Login login) {

		Integer userId = 0;
		// DEBUG
		LOG.debug(login.getUsername() + login.getPassword());
		if (login.getUsername().equals("limbach")
				&& login.getPassword().equals("1234")) {
			userId = 42;
		}

		return new ResponseEntity<Integer>(userId, HttpStatus.OK);

	}

	@RequestMapping(value = "api/users/{id}/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {

		UserService service = new UserService();
		User user = service.getUserById(id);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "api/users/search/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUser(
			@RequestParam(value = "name") String name) {

		UserService service = new UserService();
		User user = service.getUserByName(name);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
