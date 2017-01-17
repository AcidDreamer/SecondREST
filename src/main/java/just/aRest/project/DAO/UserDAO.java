package just.aRest.project.DAO;

import org.springframework.http.ResponseEntity;

import just.aRest.project.User.*;

public interface UserDAO {
	public Client getByLogin(String username,String password);
	public ResponseEntity<String> subscribe(String email,String brand);
}
