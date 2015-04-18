package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;



import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.Transaction;


public class DeleteData {
	
	 private static String DB_PATH;
	 static GraphDatabaseService db;
	 
	 public DeleteData() {
		 DB_PATH = "graph.DB";
		 if (db == null) { 
			 db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		 }
	 }

	 public void deleteNeo4j() {
		 ExecutionEngine engine = new ExecutionEngine(db);
		 ExecutionResult result; 
		 try( Transaction tx =  db.beginTx()) {
			 
			 String query = "match n delete n";
			 
			 result = engine.execute(query);
			 
			 tx.success();
		 }
		 System.out.println("---deleteNeo4j is completed-----");
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
