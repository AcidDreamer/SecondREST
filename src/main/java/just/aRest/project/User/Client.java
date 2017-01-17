package just.aRest.project.User;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class Client {
	String username;
	String password;
	String full_name;
	
	public Client(){
		
	}
	
		
	public Client(String username, String password) {
		this.username = username;
		this.password = password;
	}


	public Client(String username, String password, String fullname) {
		super();
		this.username = username;
		this.password = password;
		this.full_name = fullname;
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
	public String getFullname() {
		return full_name;
	}
	public void setFullname(String fullname) {
		this.full_name = fullname;
	}

}
