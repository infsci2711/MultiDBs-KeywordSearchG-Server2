package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.viewModels.JSONtoNeo4j;
import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.viewModels.Search;

@Path("Generate/")
public class GenerateTest {
	
	@Path("test")
	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public Response generate() throws JSONException, IOException {
		
		CreateTestData create = new CreateTestData();
		String e = create.create();
		
		//return Response.status(200).entity("[{\"row\":[{\"name\":\"Napoleon\",\"Gender\":\"Male\",\"School\":\"IS\"}]},{\"row\":[{\"name\":\"BigTree\",\"Gender\":\"Male\",\"School\":\"IS\"}]},{\"row\":[{\"course\":\"Statistics\",\"Grade\":\"3\"}]},{\"row\":[{\"course\":\"MapReduce\",\"Grade\":\"3\"}]}]").build();
		return Response.status(200).entity(e).build();
		
	}
	

	
}

