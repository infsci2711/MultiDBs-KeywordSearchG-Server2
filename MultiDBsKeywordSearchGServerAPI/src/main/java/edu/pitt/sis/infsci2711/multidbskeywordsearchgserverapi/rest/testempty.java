package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;


import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class testempty {
	
	public static JSONObject predbss(String stt) throws JSONException, IOException{
			Client client =Client.create();
			WebResource webResource = client
					.resource(stt);
			
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			
			if (response.getStatus()!=200){
				response.getEntity(String.class);
				return null;
			}
			else{
				JSONObject json3 = JsonReader.readJsonFromUrl2(stt);
//				String output = response.getEntity(String.class);
//				System.out.println(output);
//				JSONObject jo=new JSONObject(output);
//				if(jo.has("error")){
//					
//				}
//				else{
//					JSONObject schema =jo.getJSONObject("schema");
//					org.json.JSONArray columnNames =schema.getJSONArray("columnNames");
//					for(int i=0;i<columnNames.length();i++)
//					{
//						//System.out.println(columnNames.getString(i));
//					}
				return json3;
			}
			
			
		
	}
}
