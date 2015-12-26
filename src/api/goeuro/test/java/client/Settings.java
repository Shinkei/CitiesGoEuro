package api.goeuro.test.java.client;

/**
 * @author Jorge Ramirez
 * @version 0.0.1
 * 
 * This enum is used to store the settings used in the package api.goeuro.test.java.client
 */
public enum Settings {
	GO_EURO_URL_CITY_INFO("http://api.goeuro.com/api/v2/position/suggest/en/");
	
	private String setting;
	
	private Settings(String setting){
		this.setting = setting;
	}
	
	public String getSetting() {
		return setting;
	}
}
