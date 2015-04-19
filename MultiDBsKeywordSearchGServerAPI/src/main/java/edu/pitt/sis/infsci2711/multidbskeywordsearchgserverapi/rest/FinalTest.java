package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;



import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class FinalTest {
	
	public static void finaltest() throws Exception {
		LinkedList<String> urll=testmetastore.prestoinfo();
		int ir = urll.size();
		DeleteData  delete = new DeleteData();
		delete.deleteNeo4j();
		Property property = new Property();
		JSONupdate update = new JSONupdate();
		
		for (int ur = 0; ur<ir;ur++){
			String urq=urll.get(ur);
		JSONObject json8 = testempty.predbss(urq);
		if (json8==null){
			continue;
		}
		else{
		
		  
		/*List<String> URL = new LinkedList<String>();
		URL.add("http://54.174.80.167:7654/Query/34/supertest3");
		URL.add("http://54.174.80.167:7654/Query/26/copytest1");*/
		
		
			
		// remember to close BufferedReader, there are two class variable in property
		// property.container, property.output
		
		
		//JsonReader jsonRead = new JsonReader();
		
		//for(String s : URL) {
			
			//JSONObject json8 = JsonReader.readJsonFromUrl2(s);
			
			// this is to extract Database ID and table name
			//String temp2 =json8.toString();
			String[] temp = urq.split("/"); // temp[4] is Database id and temp[5] is table name
			String Did = temp[4];
			String tablename = temp[5];
			
			// this is to store {schema, JSONObject} and {data, JSONArray} into HashMap
			String content = json8.toString();
			HashMap<String, String> map = update.json(content);
			
			String schema_value = map.get("schema");
			String name = update.analyzeJSON(schema_value); // this is the name of column
			
			System.out.println( property.outputCSV(name) );
			
			String data_value = map.get("data");
			LinkedList<String> value_content = update.analyzeJSONArray(data_value);
			
			// store data into Neo4j
			for (String e : value_content) {
				update.JSON2Neo4j(name, e, Did, tablename);
			} // end inner for
			
		} // end outer for
		}
		property.outputClose(); // close BufferedWriter
		update.shutDown(); // shutDown database
	}

}
