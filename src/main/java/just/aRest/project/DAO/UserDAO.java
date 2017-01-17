package just.aRest.project.DAO;

import just.aRest.project.User.*;

public interface UserDAO {
	public Client getByLogin(String username,String password);

}
