package just.aRest.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import just.aRest.project.DAO.UserDAO;
import just.aRest.project.User.Client;

@RestController
@RequestMapping(value = "/user")
public class UserControlller {

	@Autowired
	@Qualifier("UserDAO")
	private UserDAO userDAO;

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public Client login(@RequestBody String credentials) {
		String[] splitArray = credentials.split(",");
		if (splitArray.length != 2) {
			Client client = null;
			return client;
		} else {
			Client toSent = userDAO.getByLogin(splitArray[0], splitArray[1]);
			return toSent;
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<String> subscribe(@RequestBody String form) {
		String[] splitArray = form.split(",");
		if (splitArray.length != 2) {
			return new ResponseEntity<String>("Please fill the form", HttpStatus.NOT_ACCEPTABLE);
		} else {
			ResponseEntity<String> toSent = userDAO.subscribe(splitArray[0], splitArray[1]);
			return toSent;
		}
	}

}
