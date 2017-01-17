package just.aRest.project.Controllers;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import just.aRest.project.Appl.Application;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ApplicationList")
public class ApplicationService {
	List<Application> appList;
	
	@XmlElement(name = "Application")
	public List<Application> getUserList() {
		return appList;
	}

	public void setUpList(List<Application> userList) {
		this.appList = userList;

	}
}
