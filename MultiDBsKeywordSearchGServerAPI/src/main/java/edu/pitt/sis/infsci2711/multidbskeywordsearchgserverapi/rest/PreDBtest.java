package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;



import java.io.IOException;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// import org.json.JSONException;

public class PreDBtest {
	
	public static void predb() throws Exception {
		LinkedList<String> urll=testmetastore.prestoinfo();
		int ir = urll.size();
		for (int ur = 0; ur<ir;ur++){
			String urq=urll.get(ur);
		JSONObject json8 = testempty.predbss(urq);
		if (json8==null){
			continue;
		}
		else{
		String str =json8.toString();
		JSONupdate test = new JSONupdate();
		//str = "{\"schema\":{\"columnNames\":[\"aid\",\"value\",\"bid\",\"number\"]},\"data\":[{\"row\":[\"1\",\"2\",\"2\",\"23\"]},{\"row\":[\"2\",\"3\",\"3\",\"111\"]},{\"row\":[\"3\",\"4\",\"4\",\"121\"]},{\"row\":[\"4\",\"5\",\"5\",\"992\"]}]}";
		
		//String str = "{\"schema\":{\"columnNames\":[\"aid\",\"value\",\"bid\",\"number\"]},\"data\":[{\"row\":[\"1\",\"2\",\"2\",\"23\"]},{\"row\":[\"2\",\"3\",\"3\",\"111\"]},{\"row\":[\"3\",\"4\",\"4\",\"121\"]}]}";
		
		if ( !str.startsWith("{\"error\"")) {
			test.json(str);
			//test.checkProperty();
			//test.JSON2Neo4j();
			//test.close();
			test.shutDown();
		} else {
			System.out.println("this is an empty database");
		}

		}
	}
	}
	
  
}
