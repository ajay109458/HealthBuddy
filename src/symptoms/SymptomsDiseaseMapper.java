package symptoms;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import constants.SymptomsAnalyzerConstants;
import utility.CSVManager;

public class SymptomsDiseaseMapper {

	private static SymptomsDiseaseMapper instance;
	
	private Map<String, Set<String>> symptomsByDiseaseNameMap ;
	
	private String[] symptoms;
	
	private SymptomsDiseaseMapper() {
		symptomsByDiseaseNameMap = new HashMap<>();
		symptoms = new String[0];
		
		populateSymptomsByDiseaseNameMap();
	}
	
	public static SymptomsDiseaseMapper getInstance() {
		if(instance == null) {
			instance = new SymptomsDiseaseMapper();
		}
		
		return instance;
	}
	
	public String[] getSymptoms() {
		return symptoms;
	}
	
	public Map<String, Set<String>> getSymptomsByDiseaseNameMap() {
		return symptomsByDiseaseNameMap;
	}

	private void populateSymptomsByDiseaseNameMap() {
		try {
			String[][] datasetContent = CSVManager.readContent(SymptomsAnalyzerConstants.CONFIG_FOLDER + SymptomsAnalyzerConstants.SYMPTOMS_DISEASE_DATASET2);
			
			if(datasetContent.length > 0 ) {
				
				String[] symptomsData = datasetContent[0];
			    symptoms = Arrays.copyOfRange(symptomsData, 1, symptomsData.length -1 );
				
				for(int i = 1; i < datasetContent.length; i++) {
					String[] row = datasetContent[i];
					
					String diseaseName = row[0];
					
					Set<String> symptomsList = new HashSet<>();
					for(int j = 1; j < row.length; j++) {
						try {
							if(Integer.parseInt(row[j]) == 1) {
								symptomsList.add(symptomsData[j].trim());
							}
						} catch (Exception e) {
							
						}
					}
					
					symptomsByDiseaseNameMap.put(diseaseName, symptomsList);
				} 
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
