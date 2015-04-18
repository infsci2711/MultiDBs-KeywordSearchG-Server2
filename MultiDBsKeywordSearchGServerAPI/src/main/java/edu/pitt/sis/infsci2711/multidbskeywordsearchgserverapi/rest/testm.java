package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class testm {

	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub
		JSONObject json1=new JSONObject();
		//JSONArray json2= (JSONArray) json1;
		json1 = JsonReader.readJsonFromUrl2("http://54.174.80.167:7654/Query/1/metaStore");
		System.out.println(json1);
		//int rq=a.length;
		//JsonReader sddd = new JsonReader();
		//sddd.metast();

	}
}
