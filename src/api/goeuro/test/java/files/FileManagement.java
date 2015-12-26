package api.goeuro.test.java.files;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;

/**
 * @author Jorge Ramirez
 * @version 0.0.1
 * 
 * This class provides a set of methods to handle files
 */
public class FileManagement {

	
	/**
	 * @param json array to be saved in a file
	 * @param path of the file to be created
	 * @param name of the file to be created
	 * @throws Exception
	 * 
	 * This method create a CSV file from a JSONArray object, to a given path with a given name
	 */
	public void saveJsonToCSV(JSONArray json, JSONArray names, String path, String name) throws Exception{
		
		String filePath  = "";
		if(path != null){
			filePath = path+"/"+name+".csv";
		}else{
			filePath = name+".csv";
		}
		 
		
		String fields = "";
		for (int i = 0; i < names.length(); i++) {
			
			if(i > 0){
				fields += ",";
			}
			
			fields += names.get(i).toString();
			
			if(i == names.length()-1){
				fields += "\n";
			}
		}
		
		
		
		File file=new File(filePath);
	    String csv = CDL.toString(names, json);
	    try {
	    	FileUtils.writeStringToFile(file, fields);
			FileUtils.writeStringToFile(file, csv, true);
			System.out.println("File successfully created at: "+file.getAbsolutePath());
		} catch (IOException e) {
			throw new Exception("There is an error trying to save the CSV file: " + e.getMessage());
		}
	}
}
