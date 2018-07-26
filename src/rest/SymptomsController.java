package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.AnalyzedDisease;
import symptoms.SymptomsAnalyzer;


@Path("/symptomsController")
public class SymptomsController {
	
	
	
	@GET
	@Path("/symptoms")
	public Response getSymptoms() {

		String output = SymptomsAnalyzer.getSymptoms();
		
		if(output == null || output == "") {
			output = SymptomsAnalyzer.getUserSymptomsML();
		}

		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/disease")
	public Response getDisease(@QueryParam("symptoms") List<String> symptoms) {

		List<AnalyzedDisease> analysedDisease = SymptomsAnalyzer.analyseSymptoms(symptoms);
		

		ObjectMapper objectMapper = new ObjectMapper();
		
		String value = "";
		try {
			 value = objectMapper.writeValueAsString(analysedDisease);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Response response = Response.status(200).entity(value).build();
		
		return response;
	}
}
