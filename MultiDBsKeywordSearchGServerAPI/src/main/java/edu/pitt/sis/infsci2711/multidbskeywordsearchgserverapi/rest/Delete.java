package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Delete {
	static GraphDatabaseService db;
	public static void main (String[] args) {
		 String DB_PATH = "/multidbskeywordsearchgserverapi/db/graph.DB";
		 db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		 ExecutionEngine engine = new ExecutionEngine(db);
		 ExecutionResult result; 
		 try( Transaction tx =  db.beginTx()) {
			 
			 String query = "match n delete n";
			 
			 result = engine.execute(query);
			 
			 tx.success();
		 }
		 shutDown();
	}
	
    public static void shutDown()
    {
        System.out.println();
        System.out.println( "Shutting down database …" );
        // START SNIPPET: shutdownServer
        db.shutdown();
        // END SNIPPET: shutdownServer
    } // end shutdown

}
