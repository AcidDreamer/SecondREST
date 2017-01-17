package just.aRest.project.DAO;

import java.awt.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import just.aRest.project.Appl.Application;

public class ApplDAOImpl implements ApplDAO {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Application getByUsername(String username) {
		String query = "SELECT * FROM Application WHERE USERNAME= ? LIMIT 1 ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Object queryForObject = jdbcTemplate.queryForObject(query, new Object[] { username },
				new BeanPropertyRowMapper<Application>(Application.class));
		Application app = (Application) queryForObject;

		return app;
	}

}
