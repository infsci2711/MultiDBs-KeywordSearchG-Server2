package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;

import java.io.IOException;

import javax.ws.rs.Path;

import org.json.JSONException;

import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.viewModels.JSONtoNeo4j;

//import org.json.JSONException;

public class test {
	
	
	public static void main(String[] args) throws JSONException, IOException {
		
		String str1 = "{\"name\":\"Napoleon\", \"Gender\":\"Male\", \"School\":\"IS\"}";
		String str2 = "{\"name\":\"BigTree\", \"Gender\":\"Male\", \"School\":\"IS\"}";
		String str3 = "{\"course\":\"Statistics\", \"Grade\":\"3\"}";
		String str4 = "{\"course\":\"MapReduce\", \"Grade\":\"3\"}";
		String str5 = "{\"city\":\"pitt\", \"Avenue\":\"centre\"}";
		String str6 = "{\"city\":\"DC\", \"Avenue\":\"centre\"}";
		String str7 = "{\"state\":\"PA\", \"Avenue\":\"centre\"}";
		String str8 = "{\"country\":\"China\", \"Avenue\":\"centre\"}";
		
		JSONtoNeo4j test = new JSONtoNeo4j();

		test.json_neo4j(str1);
		test.json_neo4j(str2);
		test.json_neo4j(str3);
		test.json_neo4j(str4);
		test.json_neo4j(str5);
		test.json_neo4j(str6);
		test.json_neo4j(str7);
		test.json_neo4j(str8);
		test.close();
		test.shutDown();
	}
	
	
	
//	public static void main(String[] args) {
//		
//		InputCypher ic = new InputCypher();
//		ic.checkDatabaseIsRunning();
//		String result = ic.sendTransactionalCypherQuery("IS World");
//		System.out.println(result);
//		
//	}
	/*
	public static void main(String[] args) {
		Search search = new Search();
		search.search("a");
	}
	*/
	
}
