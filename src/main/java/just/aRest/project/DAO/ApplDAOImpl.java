package just.aRest.project.DAO;

import java.awt.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import just.aRest.project.Appl.Application;
import just.aRest.project.Controllers.ApplicationController;

public class ApplDAOImpl implements ApplDAO {

	private static final Logger logger = LoggerFactory.getLogger(ApplDAOImpl.class);

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	//set datasource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Application getByUsername(String username) {
		//get the application that the user requested,criteria : 1: username , 2: application is not online 
		String query = "SELECT app_code,amount,repayTime,buy_type,drivers_license,taxes,tekmiriwsi,status,accepted,username"
				+ " FROM Application WHERE USERNAME= ? AND isOnline=0 LIMIT 1 ";
		try {
			//open jdbc connection
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			//get an object with the requested application
			Object queryForObject = jdbcTemplate.queryForObject(query, new Object[] { username },
					new BeanPropertyRowMapper<Application>(Application.class));
			//Create an application object and return it
			Application app = (Application) queryForObject;
			return app;
		} catch (EmptyResultDataAccessException e) {
			//if no results returned , create an empty application and sent it
			Application app = new Application(0, 0, 0, "", "", 0, "", 0, 0, "");
			return app;
		}
	}

	@Override
	public ResponseEntity<String> createApplication(Application app) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "SELECT email FROM Client WHERE username = ? LIMIT 1";
		try {
			//before creating the application check if the user exists
			String checker0 = jdbcTemplate.queryForObject(query, new Object[] { app.getUsername() }, String.class);
			int checker =0;
			if(checker0.equals("") || checker0 == null)
				checker = 0;
			else
				checker = 1;
			if (checker == 1) {
				//Create the application
				query = "INSERT INTO Application VALUES(?,?,?,?,?,?,?,0,0,?,10)";
				checker = jdbcTemplate.update(query,app.getApp_code(), app.getAmount(), app.getRepay_Time(),
						app.getBuy_type(), app.getDrivers_license(), app.getTaxes(), app.getTekmiriwsi(),
						app.getUsername());
				//enter empty field for Directors Commentary
				if (checker == 1) {
					query = "INSERT INTO Director VALUES(\"\",?)";
					checker = jdbcTemplate.update(query, app.getApp_code());
					if (checker == 1) {
						//Return user for successful completion
						return new ResponseEntity<String>("Opperation completed successfully", HttpStatus.OK);
					} else {
						//inform him that something went wrong
						return new ResponseEntity<String>("Very rare issue,contact technical support", HttpStatus.NOT_ACCEPTABLE);
					}
				} else {
					return new ResponseEntity<String>("Fill the form correctly", HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<String>("User doesn\'t exist", HttpStatus.NOT_ACCEPTABLE);
			}

		}catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("User doesn\'t exist", HttpStatus.NOT_ACCEPTABLE);
		}catch(NumberFormatException e){
			return new ResponseEntity<String>("Form filled incorrectly", HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
}
