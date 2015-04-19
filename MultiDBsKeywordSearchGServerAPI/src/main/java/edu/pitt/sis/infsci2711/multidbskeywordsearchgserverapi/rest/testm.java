package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.server.JerseyClientUtil;
import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.server.PropertiesManager;

public class testm {

	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub
		JSONObject json1=new JSONObject();
		//JSONArray json2= (JSONArray) json1;
		//Response result = JerseyClientUtil.doGet(PropertiesManager.getInstance().getStringProperty("http://54.174.80.167:7654"), PropertiesManager.getInstance().getStringProperty("/Query/1/metaStore"));
		json1 = JsonReader.readJsonFromUrl2("http://54.174.80.167:7654/Query/1/metaStore");
		
		if (json1==null){
			System.out.println("null");
		}
		else{
		System.out.println(json1);
		}
		
		//int rq=a.length;
		//JsonReader sddd = new JsonReader();
		//sddd.metast();
		//System.out.println(result);
	}
}
