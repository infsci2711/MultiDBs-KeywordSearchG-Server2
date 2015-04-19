package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONArray json = new JSONArray(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
  
  public static JSONObject readJsonFromUrl2(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      
	    	  return json;
	    }finally {
	      is.close();
	    }
	  }
  
  
  
  
  @SuppressWarnings("null")
public static String[] metast() throws JSONException{
	  
    JSONArray json1= new JSONArray();
	try {
		json1 = readJsonFromUrl("http://54.152.26.131:7654/datasources");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    String[] stringArray = new String[json1.length()];  
    int size = json1.length();  
    String[] metainfo = new String[size];
    for (int i = 0; i < size; i++)   
    {  
    	
        JSONObject jobj = (JSONObject) json1.get(i);  
        Iterator<String> keys = jobj.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			
			//System.out.println(key);
		}
		String val = jobj.getString("id");
		
		JSONArray val2 = (JSONArray) jobj.get("tables");
	    int size2 = val2.length();  
	   
		//String val3 = (JSONObject) val2.getString("id");
		//System.out.print(val);
		metainfo[i]=val;
		 for (int j = 0; j < size2; j++)   
		    {  
		        JSONObject jobj2 = (JSONObject) val2.get(j);  
		        Iterator<String> keys2 = jobj2.keys();
				while (keys2.hasNext()) {
					String key = keys2.next();
					
					//System.out.println(key);
				}
				String val3 = jobj2.getString("tableName");
				metainfo[i]=metainfo[i] + "," + val3;
				//System.out.print("," + val3);
		    }
	//System.out.println(metainfo[i]);
    }  
    //return stringArray;  
    return metainfo;
   // System.out.println(json1.get(2));
  }
}
