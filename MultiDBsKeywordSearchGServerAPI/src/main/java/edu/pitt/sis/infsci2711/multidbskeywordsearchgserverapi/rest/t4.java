package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("NeoSearch/")
public class t4
{
    private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
    
    /*public static String input(){
    	Scanner in = new Scanner(System.in);
    	String in2= in.next();
    	return in2;
    }*/
    
    /*public static void main( String[] args ) throws URISyntaxException
    {
        checkDatabaseIsRunning();
        sendTransactionalCypherQuery( "match ne where ne.name =~ '.*"+ "S" +".*' return ne" );
    }*/
    /*static BufferedReader br = null;
    public static LinkedList<String> query(String str)  {
        
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("property.csv")));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        LinkedList<String> queries = new LinkedList<String>();
        String currLine;
        
        if (str.contains(" ")) {
            
            String[] tempInput = str.split(" ");
            try {
                while((currLine=br.readLine()) != null) {
                    String[] temp = currLine.split(",");
                    
                    
                    for (int j = 0; j < tempInput.length; j++) {
                        String query = "";
                        for(int i = 0; i < temp.length; i++) {
                            query = query + "n."+ temp[i] +"=~ '.*" + tempInput[j] + ".*'" + " or ";
                        }
                        
                        query = "match n where " + query.substring(0,query.length()-4) + " return n";
                        queries.add(query);
                        System.out.println(query);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            
        } else {
            try {
                while((currLine=br.readLine()) != null) {
                    
                    String[] temp = currLine.split(",");
                    String query = "";
                    for(int i = 0; i < temp.length; i++) {
                        query = query + "n."+ temp[i] +"=~ '.*" + str + ".*'" + " or ";
                    }
                    query = "match n where " + query.substring(0,query.length()-4) + " return n";
                    queries.add(query);
                    System.out.println(query);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        
        return queries;

   }*/
    
	
	//@Consumes(MediaType.APPLICATION_JSON)
	/*public String output(){
		String str = "S";
		String out="";
        LinkedList<String> queries = (LinkedList<String>) query(str);
        for(String temp:queries ) {
        	out = out+sendTransactionalCypherQuery(temp);
        }
        return out;
	}*/
    
    /*public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String user=request.getParameter("kwd"); //Use request variable to do get...
        
 }*/
    String jje="";
    
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createMessage(@FormParam("kwd") String kwd2){
		jje=kwd2;
		return kwd2;
	}
    
    @Path("{keywords}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response sendTransactionalCypherQuery(@PathParam("keywords") String query) {
        // START SNIPPET: queryAllNodes
		//String query3=doPost(user);
    	InputCypher query2 = new InputCypher();
    	String query3=query2.query(query);
		//String query3="match ne where ne.Avenue =~ '.*"+ query +".*' or ne.name =~ '.*"+ query +".*' or ne.city =~ '.*"+ query +".*' or ne.state =~ '.*"+ query +".*' or ne.course =~ '.*"+ query +".*' or ne.Gender =~ '.*"+ query +".*' or ne.School =~ '.*"+ query +".*' return ne"; 
        final String txUri = SERVER_ROOT_URI + "transaction/commit";
        WebResource resource = Client.create().resource( txUri );

        String payload = "{\"statements\" : [ {\"statement\" : \"" +query3 + "\"} ]}";
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
        System.out.println(d);
        return Response.status(200).entity(d).build();
        //response.close();
        // END SNIPPET: queryAllNodes"POST [%s] to [%s], status code [%d], returned data: "
		
      
    }

    private static void checkDatabaseIsRunning()
    {
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