package just.aRest.project.User;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class Client {
	String username;
	String password;
	
	public Client(){
		
	}
	

	public Client(String username, String password, String full_name) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Client [username=" + username + ", password=" + password + "]";
	}
	
}
