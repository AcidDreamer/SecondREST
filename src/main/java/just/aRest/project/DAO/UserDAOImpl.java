package just.aRest.project.DAO;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import just.aRest.project.Appl.Application;
import just.aRest.project.User.Client;

public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Client getByLogin(String username,String password){
		String query = "SELECT username,password,full_name "
				+ "FROM User "
				+ "WHERE username =? AND PASSWORD = ?";
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			Object queryForObject = jdbcTemplate.queryForObject(query, new Object[] { username , password },
					new BeanPropertyRowMapper<Client>(Client.class));

			Client client = (Client) queryForObject;
			System.out.println(client.toString());

			return client;
		} catch (EmptyResultDataAccessException e) {
			Client client = new Client();
			return client;
		}
	}
	@Override
	public ResponseEntity<String> subscribe(String email,String brand){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "INSERT INTO Registered VALUES(?,?)";
		try {
			int checker = jdbcTemplate.update(query,email,brand);
			if(checker==1)
				return new ResponseEntity<>("Opperation completed successfully", HttpStatus.OK);
			else
				return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_ACCEPTABLE);
		}catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_ACCEPTABLE);
		}catch (DuplicateKeyException e) {
			return new ResponseEntity<>("You are already subscribed", HttpStatus.NOT_ACCEPTABLE);
		}	
		
	}
}
