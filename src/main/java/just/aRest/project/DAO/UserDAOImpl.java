package just.aRest.project.DAO;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
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
		String query = "SELECT User.username,password,full_name "
				+ "FROM User,Client "
				+ "WHERE User.username = Client.username AND Client.username =? AND PASSWORD = ?";
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			Object queryForObject = jdbcTemplate.queryForObject(query, new Object[] { username , password},
					new BeanPropertyRowMapper<Client>(Client.class));
			Client client = (Client) queryForObject;

			return client;
		} catch (EmptyResultDataAccessException e) {
			Client client = new Client();
			return client;
		}
	}
}
