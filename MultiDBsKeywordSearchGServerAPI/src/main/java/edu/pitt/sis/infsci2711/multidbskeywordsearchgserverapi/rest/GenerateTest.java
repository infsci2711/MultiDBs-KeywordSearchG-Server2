package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.pitt.sis.infsci2711.multidbs.utils.JerseyClientUtil;
import edu.pitt.sis.infsci2711.multidbs.utils.PropertiesManager;

@Path("Generate/")
public class GenerateTest {
	
	//@SuppressWarnings("static-access")
	@Path("data")
	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public Response generate() throws Exception {
		//DeleteData dl = new DeleteData();
		//dl.deleteNeo4j();
		FinalTest qw =new FinalTest();
		qw.finaltest();

		//CreateTestData create = new CreateTestData();
		//String e = create.create();
		
		//return Response.status(200).entity("[{\"row\":[{\"name\":\"Napoleon\",\"Gender\":\"Male\",\"School\":\"IS\"}]},{\"row\":[{\"name\":\"BigTree\",\"Gender\":\"Male\",\"School\":\"IS\"}]},{\"row\":[{\"course\":\"Statistics\",\"Grade\":\"3\"}]},{\"row\":[{\"course\":\"MapReduce\",\"Grade\":\"3\"}]}]").build();
		return Response.status(200).entity(qw).build();
		
	}
	
	
	
	/*public String meta() throws IOException{
		//URI uri = new URI("http://54.152.26.131:7654/datasources");
		return Response.status(200).entity(test3()).build();
	}*/
	@Path("meta")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response aa(){
		Response result = JerseyClientUtil.doGet(PropertiesManager.getInstance().getStringProperty("presto.rest.base"), PropertiesManager.getInstance().getStringProperty("presto.rest.getAllDatasources"));
		return result;
	}
	
	public static String test3() throws IOException{
		URL url = new URL("http://54.174.80.167:7654/Query/25/cars");
		URLConnection uc = url.openConnection();
		InputStream in =uc.getInputStream();
		int c;
		String output="";
		while ((c = in.read())!= -1) {
			output = output + c;
		}
			System.out.print(c);
		in.close();
		
		return output;
	}
}

