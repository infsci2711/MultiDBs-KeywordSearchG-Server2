package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.server;



import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.server.JerseyJettyServer;

public class ServerStart {

	public static void main(final String[] args) throws Exception {
		JerseyJettyServer server = new JerseyJettyServer(7654, "edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest");
		server.start();
		/*	final Driver driver = new Driver();
	    final String hostPort = "graph.sb02.stations.graphenedb.com:24789";

	    final Properties neos = new Properties();
	    neos.put("user", "graph");
	    neos.put("password", "QoFmuLo7X3alBcCI0bMh");
	    Neo4jConnection conn = driver.connect("jdbc:neo4j://" + hostPort, neos);
	    conn.*/
	}
	
}
