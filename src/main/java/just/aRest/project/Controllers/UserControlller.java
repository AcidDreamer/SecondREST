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
@RequestMapping(value = "/user")	//Controllers handles request at /user
public class UserControlller {

	@Autowired
	@Qualifier("UserDAO")
	private UserDAO userDAO;

	//gets POST data and sents login credentials
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public Client login(@RequestBody String credentials) {
		try{
			//Split string using ,
			String[] splitArray = credentials.split(",");
			//Check the length of the array
			if (splitArray.length != 2) {
				//Create and sent empty client
				Client client = new Client();
				return client;
			} else {
				//else get data and return Client
				Client toSent = userDAO.getByLogin(splitArray[0], splitArray[1]);
				return toSent;
			}
		}catch(NullPointerException e){
			Client client = new Client();
			return client;
		}
	}

	//gets POST data and subscribes user to a car brands' newsletter
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<String> subscribe(@RequestBody String form) {
		//Split string using ,
		String[] splitArray = form.split(",");
		//Check the length of the array
		if (splitArray.length != 2) {
			//Inform user that the form wasn't filled correctly.
			return new ResponseEntity<String>("Please fill the form", HttpStatus.NOT_ACCEPTABLE);
		} else {
			//Create response and return it
			ResponseEntity<String> toSent = userDAO.subscribe(splitArray[0], splitArray[1]);
			return toSent;
		}
	}

}
