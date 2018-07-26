package symptoms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import entity.AnalyzedDisease;

public class SymptomsAnalyzer {

	public static String BASE = "http://roshan-office:8000/";

	public static List<AnalyzedDisease> analyseSymptoms(List<String> userSymptoms) {

		List<AnalyzedDisease> analyzedDiseases = new ArrayList<>();

		Map<String, Set<String>> symptomsByDiseaseNameMap = SymptomsDiseaseMapper.getInstance()
				.getSymptomsByDiseaseNameMap();

		for (Entry<String, Set<String>> entry : symptomsByDiseaseNameMap.entrySet()) {

			String diseaseName = entry.getKey();
			Set<String> systemSymptoms = entry.getValue();

			int matchCount = 0;
			for (String userSymptom : userSymptoms) {
				String[] subSymptoms = userSymptom.split(",");
				for (String symptom : subSymptoms) {
					if (systemSymptoms.contains(symptom.trim())) {
						matchCount++;
					}
				}
			}

			if (matchCount > 0) {
				analyzedDiseases.add(new AnalyzedDisease(diseaseName,
						((double) (2 * matchCount * 100) / (userSymptoms.size() + systemSymptoms.size()))));
			}

		}

		Collections.sort(analyzedDiseases, new Comparator<AnalyzedDisease>() {

			@Override
			public int compare(AnalyzedDisease o1, AnalyzedDisease o2) {
				if (o1.getOccurancePercentage() > o2.getOccurancePercentage()) {
					return -1;
				} else if (o1.getOccurancePercentage() < o2.getOccurancePercentage()) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		analyzedDiseases = analyzedDiseases.subList(0, 9);

		return analyzedDiseases;
	}

	public static String getSymptoms() {
		String[] symptoms = SymptomsDiseaseMapper.getInstance().getSymptoms();

		Gson gson = new Gson();
		return gson.toJson(symptoms);
	}

	public static String analyseSymptomsML(List<String> userSymptoms) {

		final Client client = ClientBuilder.newBuilder().build();

		Gson gson = new Gson();

		String jsonData = gson.toJson(userSymptoms.toArray(new String[userSymptoms.size()]));

		final WebTarget target = client.target(BASE + "disease/");
		final Response response = target.request().post(Entity.entity(jsonData, MediaType.APPLICATION_JSON));

		// Use response object to verify upload success
		System.out.println(response.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.readEntity(String.class);

		return output;

	}

	public static String getUserSymptomsML() {
		final Client client = ClientBuilder.newBuilder().build();

		final WebTarget target = client.target(BASE + "symptoms/");
		final Response response = target.request().get();

		// Use response object to verify upload success
		System.out.println(response.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.readEntity(String.class);

		return output;
	}
}
