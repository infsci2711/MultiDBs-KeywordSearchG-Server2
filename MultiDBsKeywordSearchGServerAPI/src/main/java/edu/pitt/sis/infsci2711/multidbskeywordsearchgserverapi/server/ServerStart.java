package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.server;

import java.io.File;

import edu.pitt.sis.infsci2711.multidbs.utils.JerseyJettyServer;
import edu.pitt.sis.infsci2711.multidbs.utils.PropertiesManager;

public class ServerStart {
	private final static String PROPERTY_PORT = "port";
	private final static int DEFAULT_PORT = 7654;
	
	public static void main(final String[] args) throws Exception {
		
		if (args.length > 0) {
			String propertiesFilePath = args[0]; //"src/main/resources/config.properties";
			File propertiesFile = new File(propertiesFilePath);
			PropertiesManager.getInstance().loadProperties(propertiesFile);
		}
		
		final JerseyJettyServer server = new JerseyJettyServer(PropertiesManager.getInstance().getIntProperty(PROPERTY_PORT, DEFAULT_PORT), 
				"edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest");
		
		server.start();
		
//		Thread serverTread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					server.start();
//				} catch (Exception e) {
//					throw new RuntimeException(e);
//				}
//			}
//		});
//		
//		serverTread.start();
//		
//		System.out.println("NOTE: To stop the server, focus on console and hit enter");
//		System.in.read();
//		
//		server.stop();
	}
	
}
