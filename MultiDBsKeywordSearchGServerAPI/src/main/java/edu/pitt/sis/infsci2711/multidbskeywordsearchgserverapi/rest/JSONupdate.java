package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class JSONupdate {
	
	 private static String DB_PATH;
	 GraphDatabaseService db;
	 
	 BufferedWriter output = null;
	 BufferedReader br = null;
	 
	 String file;
	 Set<String> contain;	// contain contains each line of property
	 Set<String[]> container; // container contains each line of property, store properties into String Array
	 
	 Map<String,String> schema_data;
	 
	 public JSONupdate() {
		 
		 schema_data  = new HashMap<String,String>();
		 container = new HashSet<String[]>();
		 contain = new HashSet<String>();
		 file = "property.csv";
		 DB_PATH = "graph.DB";
		 
		 // initialize GraphDatabaseService
		 if (db == null) { 
			 db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		 }
		 
		 // initialize a BufferedWriter
		 try {
			 output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true))); 
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 // initialize a BufferedReader, read each line of property.csv
		 try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		 } catch (FileNotFoundException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 // store the each line of property into contain and container
		 try {
			 String str;
			 while( (str = br.readLine()) != null) {
				 String[] temp = str.split(",");
				 contain.add(str);
				 container.add(temp);
			 }
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 // close BufferedReader
		 try {
			 br.close();
		 } catch(IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
	 }
	 
	 /**
	  * This method is used to resolve the JSON from URL
	  * key is the schema. It is a JSON object. 
	  * value is the data. It is a JSONArray.
	  * @param url. url is the JSON crawled from URL
	  * @return An String Array containing two elements. First is key, second is value
	  * @throws JSONException
	  */
	 public void json (String url) throws JSONException{
		System.out.println("--------begin json--------");
		JSONObject obj = new JSONObject(url);
		Iterator<String> keys = obj.keys();

		 String key = "";
		 String value = "";
		 
		while (keys.hasNext()) {
			key = keys.next();
			System.out.println(key);
			value = obj.getString(key);
			System.out.println(value);
			schema_data.put(key,value);
		} // end while 
		System.out.println("--------end json---------");
		
		if (schema_data.containsKey("schema") && schema_data.containsKey("data")) {
			System.out.println("---url to json is true---");
		} else {
			System.out.println("---url to json is false---");
		}
		System.out.println();
	 } // end json
	 
	 String column = "";
	 /**
	  * initialize column 
	  * check whether the Property.csv contains column name or not
	  * @throws JSONException 
	  */
	 public void checkProperty() throws JSONException {
		 System.out.println("------checkProperty begin-------");
		 String schema = schema_data.get("schema");
		 column = ResolveSchema(schema); // column contains property name, split by ","
		 System.out.println("column: " + column);
		 try {
			 if ( !contain.contains(column)) {
				 output.write(column + "\n");
				 System.out.println("output the column name to Property.csv");
			 } else {
				 System.out.println("Property.csv contains the column name");
			 }
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 	// close BufferedWriter
			try {
				output.close();
			} catch(IOException e) {
				 System.out.println(e.getMessage());
				 e.printStackTrace();
			}
		System.out.println("------checkProperty end-------");
		System.out.println();
	 }
	 
	 /**
	  * schema = {"columnNames":["aid","value","bid","number"]}, first resolve this schema
	  * columnNames is the key, ["aid","value","bid","number"] is the value
	  * we will return     aid,value,bid, number
	  * this method is used to extract property name, return the properties names separated by comma. 
	  * @param schema 
	  * @return
	  * @throws JSONException
	  */
	 public String ResolveSchema(String schema) throws JSONException {
		 System.out.println("------ResolveSchema start-------");
		 System.out.println("shhema: " + schema);
		 String str = "";
		 
		 JSONObject obj = new JSONObject(schema);
		 Iterator<String> keys = obj.keys();
		 
		while (keys.hasNext()) {
			String key = keys.next();
			String val = obj.getString(key);
			str = str + val.substring(0, val.length()-1);
			//String[] pair = {key, val};
		}
		String[] temp = str.split(",");
		String result = "";
		for (String s : temp) {
			result = result + s.substring(1,s.length()-1) + ",";
		}
		result = result.substring(1,result.length()-1);
		System.out.println("-----ResolveSchema end-------");
		System.out.println();
		return result;
		
	 }
	 
	 /**
	  * 
	  * this method is to resolve each JSONObject in JSONArray
	  * @param data, data looks like this 1,2,2,23
	  * @throws JSONException
	  */
	 public void Neo4j(String data) throws JSONException {
		System.out.println("-----Neo4j begin------");
		data = data.substring(1,data.length()-1);
		System.out.println(data);
		String[] temp_data= data.split(",");
		String[] temp_column = column.split(",");
		
		try( Transaction tx =  db.beginTx()) {
			Node node = db.createNode();  // create a node
			for (int i = 0; i < temp_data.length; i++) {
				node.setProperty(temp_column[i], temp_data[i]);
			}			
			tx.success();			 
		 } 
		System.out.println("add json to neo4j is done");
		System.out.println("-----Neo4j end------");
		System.out.println();
	 }
	 
	 /*
	 public void Neo4j(String data) throws JSONException {
		
		JSONObject obj = new JSONObject(data);
		Iterator<String> keys = obj.keys();
		String str = ""; 
		
		while (keys.hasNext()) {
			String key = keys.next();
			String val = obj.getString(key);
			str = str + val.substring(0, val.length()-1);
		}
		
		String[] temp = str.substring(1).split(",");
		String result = "";
		for (String s : temp) {
			result = result + s.substring(1,s.length()-1) + ",";
		}
		result = result.substring(0,result.length()-1); // result json contain data, split by ","
		System.out.println(result);
		
		String[] temp_data= result.split(",");
		String[] temp_column = column.split(",");
		
		try( Transaction tx =  db.beginTx()) {
			Node node = db.createNode();  // create a node
			for (int i = 0; i < temp_data.length; i++) {
				node.setProperty(temp_column[i], temp_data[i]);
			}			
			tx.success();			 
		 } 
		System.out.println("add json to neo4j is done");
		
	 }
	 */
	 
	 
	 /**
	  * store an JSON object into Neo4j
	  * @param str, this is an JSON object
	 * @throws JSONException 
	  */
	 public void JSON2Neo4j() throws JSONException{
		 // get the value in data, in below form
		 // [{"row":["1","2","2","23"]},{"row":["2","3","3","111"]},{"row":["3","4","4","121"]},{"row":["4","5","5","992"]}]
		 String data = schema_data.get("data"); 
		 
		 JSONArray array = new JSONArray(data);
		 
		 for (int i = 0; i < array.length(); i++) {
			 // row is json object, {"row":["1","2","2","23"]
			 // we need to resolve  this json object
			 JSONObject object = (JSONObject) array.get(i); 
			 Iterator<String> keys = object.keys();
			 
			 while (keys.hasNext()) {
				 String key = keys.next(); // key equals "row"
				 String value_array = object.getString(key); // value_array equals ["1","2","2","23"]
				 value_array.substring(1,value_array.length()-1);
				 String result = "";
				 String[] temp = value_array.split(",");
				 for (String s : temp) {
					 result = result + s.substring(1,s.length()-1)+",";
				 }
				 result = result.substring(0,result.length()-1);
				 Neo4j(result);
			 }
			 //Neo4j(row);
		 }
		 System.out.println();
		 
	 }
	 
	 /**
	  * close BufferedReader
	  */
	 void close() { 
			try {
				output.close();
			} catch(IOException e) {
				 System.out.println(e.getMessage());
				 e.printStackTrace();
			}
	 } // end close
	 
	 /**
	  * shutDown database Neo4j
	  */
	 public void shutDown()
	    {
	      System.out.println();
	      System.out.println( "Shutting down database ..." );
	      // START SNIPPET: shutdownServer
	      db.shutdown();
	      // END SNIPPET: shutdownServer
	    } // end shutdown

}
