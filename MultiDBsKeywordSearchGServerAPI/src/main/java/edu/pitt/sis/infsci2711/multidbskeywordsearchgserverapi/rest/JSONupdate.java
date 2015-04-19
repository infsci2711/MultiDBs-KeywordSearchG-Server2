package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;



import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
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
	 
	 Map<String,String> schema_data; // resolve the outer json 
	 
	 public JSONupdate() {
		 
		 schema_data  = new HashMap<String,String>();

		 DB_PATH = "graph.DB";
		 
		 // initialize GraphDatabaseService
		 if (db == null) { 
			 db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		 }
		 
	 } // end JSONupdate
	 
	 /**
	  * This method is used to resolve the JSON from URL
	  * key is the schema. It is a JSON object. 
	  * value is the data. It is a JSONArray.
	  * @param url. url is the JSON crawled from URL
	  * @return return a HashMap, there are two keys, "schema" and "data"
	  * @throws JSONException
	  */
	 public HashMap<String, String> json (String url) throws JSONException{
		System.out.println("--------begin json--------");
		
		HashMap<String, String> map = new HashMap<String, String>();
		JSONObject obj = new JSONObject(url);
		Iterator<String> keys = obj.keys();

		while (keys.hasNext()) {
			String key = keys.next();
			System.out.println(key);
			String value = obj.getString(key);
			System.out.println(value);
			map.put(key, value);
		} // end while 
		
		System.out.println("--------end json---------");
		return map;
		
	 } // end json
	 	 
	 /**
	  * this method can analyze an JSONObject, return JSON value
	  * {"columnNames":["aid","value","bid","number"]} will return aid,value,bid,number
	  * @param str
	  * @return
	  * @throws JSONException
	  */
	 public String analyzeJSON(String str) throws JSONException {
		 
		 JSONObject obj = new JSONObject(str);
		 Iterator<String> keys = obj.keys();
		 String value = "";
		 
		 while(keys.hasNext()) {
			 String key = keys.next();
			 value = obj.getString(key);
		 }
		 
		 value = value.substring(1,value.length()-1);
		 String result = "";
		 String[] temp = value.split(",");
		 
		 for (String e : temp) {
			 result = result + e.substring(1, e.length()-1) + ",";
		 }
		 
		 result = result.substring(0, result.length()-1);
		 return result;
	 } // end analyzeJSON
	 
	 /**
	  * [{"row":["1","2","2","23"]},{"row":["2","3","3","111"]},{"row":["3","4","4","121"]},{"row":["4","5","5","992"]}]
	  * this will return (1,2,2,23), (2,3,3,111), (3,4,4,121), (4,5,5,992)
	  * this method is to extract value in JSONArray
	  * @param str, this is an JSONArray
	  * @return
	  * @throws JSONException 
	  */
	 public LinkedList<String> analyzeJSONArray(String str) throws JSONException {
		 
		 LinkedList<String> result = new LinkedList<String>();
		 JSONArray array = new JSONArray(str);
		 
		 for (int i = 0; i < array.length(); i++) {
			 // we need to resolve  this json object
			 JSONObject object = (JSONObject) array.get(i); 
			 String s = object.toString();
			 System.out.println("s");
			 result.add(analyzeJSON(s));
		 }
		 
		 return result;
	 }
	 
	 /**
	  * store data into neo4j, store database ID and table name into Neo4j
	  * @param property, this is property name
	  * @param data, this is the value of property
	  * @param Did, this is database ID
	  * @param tablename, this is table name
	  */
	 public void JSON2Neo4j(String property, String data, String Did, String tablename) {
		 System.out.println("----begin JSON2Neo4j----");
		 String[] temp_property = property.split(",");
		 String[] temp_data = data.split(",");
		 
		 try( Transaction tx =  db.beginTx()) {
			 Node node = db.createNode(); 
			 for (int i = 0; i < temp_data.length; i++) {
				 node.setProperty(temp_property[i], temp_data[i]);
			 }
			 node.setProperty("Did",Did);
			 node.setProperty("tableName",tablename);
			 tx.success();
		 }

		 System.out.println("----end JSON2Neo4j----");
	 } // end JSON2Neo4j
	 

	 
	 /**
	  * shutDown database Neo4j
	  */
	 public void shutDown() {
	      System.out.println();
	      System.out.println( "Shutting down database ..." );
	      // START SNIPPET: shutdownServer
	      db.shutdown();
	      // END SNIPPET: shutdownServer
 	 } // end shutdown

}
