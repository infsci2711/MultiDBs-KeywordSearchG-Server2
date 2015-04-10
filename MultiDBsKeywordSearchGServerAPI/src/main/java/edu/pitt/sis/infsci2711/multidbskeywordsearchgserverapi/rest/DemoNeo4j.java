package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("Demo/")
public class DemoNeo4j {

	@Path("demoneo4j")
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
        EmbeddedNeo4j hello = new EmbeddedNeo4j();
        hello.createDb();
        hello.removeData();
        hello.shutDown();
        
		return Response.status(200).entity(hello.greeting).build();
		
	}
	
	

}
