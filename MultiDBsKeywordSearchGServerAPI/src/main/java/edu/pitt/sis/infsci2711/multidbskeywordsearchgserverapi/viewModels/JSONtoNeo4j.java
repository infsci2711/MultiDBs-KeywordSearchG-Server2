package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.viewModels;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.util.*;
import java.io.*;


public class JSONtoNeo4j {
	
	 private static String DB_PATH;
	 GraphDatabaseService db;
	 
	 BufferedWriter output = null;
	 BufferedReader br = null;
	 
	 String file;
	 Set<String> contain;
	 Set<String[]> container;
	 
	 public JSONtoNeo4j () {
		 
		 container = new HashSet<String[]>();
		 contain = new HashSet<String>();
		 file = "property.csv";
		 DB_PATH = "target/graph.db";
		 //DB_PATH = "database";
		 //DB_PATH = "neo4j-community-2.2.0/data/graph.db";
		 
		 if (db == null) {
			 db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		 }

		 try {
			 output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true))); 
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		 } catch (FileNotFoundException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
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
		 
		 try {
			 br.close();
		 } catch(IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
	 }
	 
	 public void json_neo4j (String str) throws JSONException, IOException {
		 
		JSONObject obj = new JSONObject(str);
		Iterator<String> keys = obj.keys();
		
		List<String[]> data = new LinkedList<String[]>();
		
		String currProp = "";
		
		while (keys.hasNext()) {
			String key = keys.next();
			String val = obj.getString(key);
			String[] pair = {key, val};
			data.add(pair);
			currProp = currProp + key +  ",";
		}

		currProp = currProp.substring(0,currProp.length()-1);
		String[] temp = currProp.split(",");
		// if the container do not contain array temp, output to the file and add array temp to container
//		if ( !container.contains(temp)) {
//			output.write(currProp + "\n");
//			container.add(temp);
//		} 
		if ( !contain.contains(currProp)) {
			output.write(currProp + "\n");
			contain.add(currProp);
		}
		
		// add data into neo4j
		try( Transaction tx =  db.beginTx()) {
			Node node = db.createNode();  // create a node
			for(String[] pair: data) {
				node.setProperty(pair[0], pair[1]);
			}
			System.out.println("add json to neo4j is done");			
			tx.success();			 
		 } //end try

	 } // end json_neo4j
	 
	 public void close() { // close BufferedWriter
			try {
				output.close();
			} catch(IOException e) {
				 System.out.println(e.getMessage());
				 e.printStackTrace();
			}
	 } // end close
	 public void shutDown() {
		// TODO Auto-generated method stub
		 System.out.println();
	        System.out.println( "Shutting down database …" );
	        // START SNIPPET: shutdownServer
	        db.shutdown();
		
	}
}
