package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi;



import edu.pitt.sis.infsci2711.multidbs.utils.JerseyJettyServer;

public class TutorialServer {

	public static void main(final String[] args) throws Exception {
		JerseyJettyServer server = new JerseyJettyServer(7648, "edu.pitt.sis.infsci2711.tutorial.rest");
		server.start();

	}
	
}
