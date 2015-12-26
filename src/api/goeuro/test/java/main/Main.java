package api.goeuro.test.java.main;

import org.json.JSONArray;
import org.json.JSONObject;

import api.goeuro.test.java.client.ClientRest;
import api.goeuro.test.java.files.FileManagement;

/**
 * @author Jorge Ramirez
 * Main class, this class will get a name from the execution parameters,
 * then obtain information about the cities with this name form the GoEuro
 * REST API and save it in a CSV file
 */
public class Main {

	/**
	 * @param args name of the city you want yo get info
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		String cityName;
		if (args.length > 0) {
			cityName = args[0];
		}
		else{
			throw new Exception("There is no Argument in the excecution, Please add a city name");
		}

		//Create a client Object and call the method to reach the REST API and
		//get the city information
		ClientRest client = new ClientRest();
		JSONArray json = client.getGoEuroCityInfo(cityName);
		
		//separate longitude and latitude from the nested object
		//put the values to be reachable when the file is saved as CSV
		for (int i = 0; i < json.length(); i++) {
			JSONObject object = json.getJSONObject(i);
			JSONObject geoPosition = object.getJSONObject("geo_position");
			object.put("latitude", geoPosition.get("latitude"));
			object.put("longitude", geoPosition.get("longitude"));
		}
		
		//Array with the field names that I want to save
		JSONArray names = new JSONArray();
		names.put("_id");
		names.put("name");
		names.put("type");
		names.put("latitude");
		names.put("longitude");
		
		//Create a file management object to save the JSONArray as a CSV File
		//the file will be created in the same path as the project or jar file
		//and will be named as the city name
		FileManagement fileManagement = new FileManagement();
		fileManagement.saveJsonToCSV(json, names, null, cityName);

	}

}
