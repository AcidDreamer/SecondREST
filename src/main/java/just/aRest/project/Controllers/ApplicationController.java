package just.aRest.project.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import just.aRest.project.Appl.Application;
import just.aRest.project.DAO.ApplDAO;


@RestController
@RequestMapping(value = "/applications")
public class ApplicationController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	@Qualifier("ApplDAO")
	private ApplDAO applDAO;

	
	@Autowired
	ApplicationService objServices;

	@RequestMapping(value = "/for_user/{username}", method = RequestMethod.GET)
	public Application getApp(@PathVariable("username") String username){
		Application app = applDAO.getByUsername(username);
		return app;
		
	}
	
}
