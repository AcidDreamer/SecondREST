package just.aRest.project.DAO;

import org.springframework.http.ResponseEntity;

import just.aRest.project.Appl.Application;

public interface ApplDAO {
	public Application getByUsername(String username);
	public ResponseEntity<String> createApplication(Application Application);

}
