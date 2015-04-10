package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class InputCypher {
	
    private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
    static BufferedReader br;
    //String str;
    String cypher;
    
    /**
     * this is the constructor method, receive an String 
     * @param str
     */
    public InputCypher() {
    	//this.str = str;
    }
    
    /**
     * receive an input search from front end and return an string representation of cypher query
     * @param str input from front end
     * @return Cypher query
     */
    public static String query(String str)  {
    	 // initialize a BufferedReader
		 try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream("property.csv")));
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
    
    /**
     * 
     * @param input
     * @return 
     */
    public String sendTransactionalCypherQuery(String str) {
        // START SNIPPET: queryAllNodes
		//String query3=doPost(user);
		
		// convery an input to cypher query
		String query = query(str);
				

		//query="";
		//String query2="match ne where ne.Avenue =~ '.*"+ query +".*' or ne.name =~ '.*"+ query +".*' or ne.city =~ '.*"+ query +".*' or ne.state =~ '.*"+ query +".*' or ne.course =~ '.*"+ query +".*' or ne.Gender =~ '.*"+ query +".*' or ne.School =~ '.*"+ query +".*' return ne"; 
        final String txUri = SERVER_ROOT_URI + "transaction/commit";
        WebResource resource = Client.create().resource( txUri );

        String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
        ClientResponse response = resource
                .accept( MediaType.APPLICATION_JSON )
                .type( MediaType.APPLICATION_JSON )
                .entity( payload )
                .post( ClientResponse.class );
        
        String ab = String.format("POST [%s] to [%s], status code [%d], returned data: " +
                System.getProperty( "line.separator" ) + "%s",
                payload, txUri, response.getStatus(),
                response.getEntity( String.class ) ) ;
        String ab2[] = ab.split("data\":");
        String ab3[] =ab2[1].split("}],\"er");
        String d=ab3[0];
        //System.out.println(d);
        return d;
        //response.close();
        // END SNIPPET: queryAllNodes"POST [%s] to [%s], status code [%d], returned data: "
		
    }
    
    /**
     * checkDatabaseIsRunning
     * return nothing
     */
    static void checkDatabaseIsRunning() {
        // START SNIPPET: checkServer
        WebResource resource = Client.create()
                .resource( SERVER_ROOT_URI );
        ClientResponse response = resource.get( ClientResponse.class );

        System.out.println( String.format( "GET on [%s], status code [%d]",
                SERVER_ROOT_URI, response.getStatus() ) );
        response.close();
        // END SNIPPET: checkServer
    }
    
}