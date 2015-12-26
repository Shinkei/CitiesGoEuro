package api.goeuro.test.java.client;

import org.json.JSONArray;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Jorge Ramirez
 * @version 0.0.1
 *
 * This class provides a set of clients to use the RESP API provided by GoEuro
 */
public class ClientRest {

	
	/**
	 * @param city name to get info about
	 * @return JSON array with all the info about the city
	 * @throws Exception
	 * 
	 * This method is the one that creates the client for the go euro REST api, and returns 
	 * a JSONArray object that represents the information of the cities provided by the 
	 * web service
	 */
	public JSONArray getGoEuroCityInfo (String city) throws Exception{
		
		
		Client client = Client.create();
		WebResource resource = client.resource(Settings.GO_EURO_URL_CITY_INFO.getSetting() + city);
		ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			throw new Exception("Error trying to get the service, HTTP error: " + response.getStatus());			
		}
		
		String citiesString = response.getEntity(String.class);
		JSONArray json = new JSONArray(citiesString);
		
		return json;
	}
	
}
