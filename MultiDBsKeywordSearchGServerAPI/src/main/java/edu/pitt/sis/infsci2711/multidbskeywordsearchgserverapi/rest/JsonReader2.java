package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader2 {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    String cp=rd.toString();
   
      sb.append(cp);
    if (sb.toString()=="{\"error\" : \"Exception\" , \"message\" :  \"did '1' does not exist\"}"){
    	return "";
    }
    return sb.toString();
  }
 
  public static String readJsonFromUrl(String url) throws IOException, JSONException {
	  String vb = new URL("http://54.174.80.167:7654/Query/1/metaStore").toString();
    /*InputStream is = new URL("http://54.174.80.167:7654/Query/1/metaStore").openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return vb;
    } finally {
      is.close();
    }*/
	  return vb;
  }

}