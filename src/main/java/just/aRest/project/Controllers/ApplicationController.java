package just.aRest.project.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import just.aRest.project.Appl.Application;
import just.aRest.project.DAO.ApplDAO;

@RestController
@RequestMapping(value = "/applications")	//Controller receivers requests at /applications
public class ApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	@Qualifier("ApplDAO")
	private ApplDAO applDAO;

	@Autowired
	ApplicationService objServices;
	
	//Returns an xml application that was requested depending on the users ,username
	@RequestMapping(value = "/for_user/{username}", method = RequestMethod.GET)
	public Application getApp(@PathVariable("username") String username) {
		Application app = applDAO.getByUsername(username);
		return app;
	}

	//Creates an application using the post data
	@RequestMapping(method = RequestMethod.POST, value = "/createApplication")
	public ResponseEntity<String> createApp(@RequestBody String Application) {
		//initialize random number to be used as the application's code 
		int appCode = 000000000 + (int) (Math.random() * 999999999);
		//Split received data 
		String[] splitArray = Application.split(",");
		//If there is not the correct amount of data return a message accordingly
		if (splitArray.length != 6) {
			Application app = null;
			return new ResponseEntity<String>("Form is filled incorrectly!", HttpStatus.OK);
		} else {
			//Create a new application with the data received
			Application app = new Application(appCode, Integer.parseInt(splitArray[0]), Integer.parseInt(splitArray[1]),
					splitArray[2], splitArray[3], Integer.parseInt(splitArray[4]), " ", 0, 0, splitArray[5]);
			//Returned proccessed element as string
			ResponseEntity<String> anApp = applDAO.createApplication(app);
			return anApp;
		}
	}

}
