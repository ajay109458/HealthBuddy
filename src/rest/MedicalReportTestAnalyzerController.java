package rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import constants.RESTConstants;
import entity.AnalyzedMedicalTestReport;
import report.MedicalReportAnalyzer;
import utility.FileManager;


@Path("/reportanalyzer")
public class MedicalReportTestAnalyzerController {
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "REST API working : " + msg;

		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/uploadReport")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadReport(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails) {

		String fileLocation = RESTConstants.TEMP_DIRECTORY_PATH + fileDetails.getFileName();

		FileManager.writeBinaryFile(fileLocation, uploadedInputStream);
		AnalyzedMedicalTestReport analyzedMedicalTestReport = MedicalReportAnalyzer.getAnalysedMedicalReport(fileLocation);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String value = "";
		try {
			 value = objectMapper.writeValueAsString(analyzedMedicalTestReport);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Response response = Response.status(200).entity(value).build();
		
		return response;
	}
}
