package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.server;



import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.server.JerseyJettyServer;

public class ServerStart {

	public static void main(final String[] args) throws Exception {
		JerseyJettyServer server = new JerseyJettyServer(7648, "edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest");
		server.start();

	}
	
}
