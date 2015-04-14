package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.viewModels;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.*;
//import org.neo4j.cypher.ExecutionEngine;
//import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.ReadableIndex;
import org.neo4j.graphdb.index.UniqueFactory;
import org.neo4j.kernel.TopLevelTransaction;
import org.neo4j.tooling.GlobalGraphOperations;
import org.neo4j.graphdb.DynamicLabel; // add a label
import org.neo4j.graphdb.Label;

import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest.CreateTestData;
import scala.util.parsing.json.JSON;



public class Search {
	
    static BufferedReader br;
	ExecutionEngine engine;	
    private String DB_PATH;
	private GraphDatabaseService db;
	
	//String keyword = "Tree";
	
	 public Search () {

		 DB_PATH = "/multidbskeywordsearchgserverapi/db/graph.DB";
		 //DB_PATH = "/opt/project/MultiDBs-KeywordSearchG-Server2/MultiDBsKeywordSearchGServerAPI/";
		 db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );

	 }
    
    public String search (String keyword){
    	
    	
    	String BigResult = "[";
		engine = new ExecutionEngine(db);
		ExecutionResult result; 
		//String output;
		
		String query = query(keyword);
		
		try ( Transaction tx = db.beginTx() ) {
			
			result = engine.execute(query);
			//output = result.toString();
			Iterator<Object> columnAs = result.columnAs("n");
			
			while(columnAs.hasNext()) {
				
				Node n = (Node)columnAs.next();
				String output = "{";
				
				for (String key : n.getPropertyKeys()) {
					output = output + "\"" + key + "\":\"" + n.getProperty(key) + "\",";
					//System.out.println("{ " + key + " : " + n.getProperty(key)+ " } ");
				} // end for
				
				output = output.substring(0,output.length()-1) + "}";
				BigResult = BigResult + output + ",";
				System.out.println(output);
				
		   } // end while
			
			tx.success();	
			
		} // end try
		
		BigResult = BigResult.substring(0,BigResult.length()-1) + "]";
		System.out.println(BigResult);
		return BigResult; 

    } // end search
    
    
    
    
	/**
     * receive an input search from front end and return an string representation of cypher query
     * @param str input from front end
     * @return Cypher query
     */
    public String query(String str)  {
    	 // initialize a BufferedReader
		 try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream("/multidbskeywordsearchgserverapi/db/property.csv")));
		 } catch (FileNotFoundException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 // this is the query that will be returned
		 String query = "";
		 // use a HashSet to store the property in "property.csv"
		 Set<String> propertySet = new HashSet<String>();
		 try {
			 
			 String currLine;
			 while((currLine=br.readLine()) != null) {
				 // each currLine is separated by a comma
				 String[] temp = currLine.split(",");
				 for(int i = 0; i < temp.length; i++) {
					 propertySet.add(temp[i]); // ad each property to HashSet
				 }
			 }

		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 if (str.contains(" ")) { // if the input contains Serveral key words
			 String[] tempInput = str.split(" ");	 
			 for (String input: tempInput) { // use two big for loop
				 for (String curr: propertySet) {
					 query = query + "n." + curr + "=~ '.*" + input + ".*'" + " or ";
				 }
			 }
		 } else { // input only contain one key word
			 for (String curr: propertySet) {
				 query = query + "n." + curr + "=~ '.*" + str + ".*'" + " or ";
			 }
		 }
		 
		 query = "match n where " + query.substring(0,query.length()-4) + " return n";
		 System.out.println(query);
		 
		 try { // close BufferedReader
			 br.close();
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 return query;
    }
    public void shutDown()
    {
        System.out.println();
        System.out.println( "Shutting down database …" );
        // START SNIPPET: shutdownServer
        db.shutdown();
        // END SNIPPET: shutdownServer
    } // end shutdown

}

