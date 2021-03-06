package just.aRest.project.Appl;
import javax.xml.bind.annotation.XmlRootElement;

//this will be an XML element which will be used to respond to requests.
@XmlRootElement(name = "application")
public class Application {

	int app_code;
	int amount;
	int repayTime;
	String buy_type;
	String drivers_license;
	int taxes;
	String Tekmiriwsi;
	int status;
	int accepted;
	String username;
	
	public Application(){
		
	}
	
	
	public Application(int app_code, int amount, int repayTime, String buy_type, String drivers_license, int taxes,
			String tekmiriwsi, int status, int accepted, String username) {
		super();
		this.app_code = app_code;
		this.amount = amount;
		this.repayTime = repayTime;
		this.buy_type = buy_type;
		this.drivers_license = drivers_license;
		this.taxes = taxes;
		Tekmiriwsi = tekmiriwsi;
		this.status = status;
		this.accepted = accepted;
		this.username = username;
	}
	
	public int getApp_code() {
		return app_code;
	}
	public void setApp_code(int app_code) {
		this.app_code = app_code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRepay_Time() {
		return repayTime;
	}
	public void setRepay_Time(int repayTime) {
		this.repayTime = repayTime;
	}
	public String getBuy_type() {
		return buy_type;
	}
	public void setBuy_type(String buy_type) {
		this.buy_type = buy_type;
	}
	public String getDrivers_license() {
		return drivers_license;
	}
	public void setDrivers_license(String drivers_license) {
		this.drivers_license = drivers_license;
	}
	public int getTaxes() {
		return taxes;
	}
	public void setTaxes(int taxes) {
		this.taxes = taxes;
	}
	public String getTekmiriwsi() {
		return Tekmiriwsi;
	}
	public void setTekmiriwsi(String tekmiriwsi) {
		Tekmiriwsi = tekmiriwsi;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAccepted() {
		return accepted;
	}
	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return "Application [app_code=" + app_code + ", amount=" + amount + ", repayTime=" + repayTime + ", buy_type="
				+ buy_type + ", drivers_license=" + drivers_license + ", taxes=" + taxes + ", Tekmiriwsi=" + Tekmiriwsi
				+ ", username=" + username + "]";
	}
	
	
}
