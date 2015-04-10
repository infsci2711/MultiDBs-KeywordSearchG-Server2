package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;


import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Test2 {
	
	public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	String b = in.nextLine();
	RunQuery(b);
	}
	
	
	public static void RunQuery(String _query)
	{
	    Properties prop = new Properties();
	    final String DB_PATH = "data/db45";
	    GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	    Node myNode = graphDb.createNode();
	    
        try ( Transaction tx = graphDb.beginTx())
        {
            
            myNode.setProperty( "name", "my node3" );
            //String c= myNode.toString();
            tx.success();
            //ystem.out.println(c);
        }
  
        
	    ExecutionEngine engine = new ExecutionEngine(graphDb);
	    ExecutionResult result = engine.execute(_query);
	    
	    for(Map<String,Object> map : result)
	    {
	        System.out.println(map.toString());
	        
	    }
	    
	    
	    
	    graphDb.shutdown();


	}
}
