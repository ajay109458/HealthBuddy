package report;

import java.util.HashMap;
import java.util.Map;

import constants.ReportAnalyzerConstants;
import entity.MedicalTestMetaData;
import utility.CSVManager;

public class MedicalTestMetadataManager {

	private static MedicalTestMetadataManager instance;

	private Map<String, MedicalTestMetaData> medicalTestMetaDataByNameMap;

	private MedicalTestMetadataManager() {
		super();
		
		medicalTestMetaDataByNameMap = new HashMap<>();
		
		populateMedicalTestsDefaultValue();
	}

	private void populateMedicalTestsDefaultValue() {

		String configFilePath = ReportAnalyzerConstants.CONFIG_FOLDER + ReportAnalyzerConstants.MEDICAL_TEST_DEFAULT_VALUES_CONFIG;
		try {
			String[][] defaultValuesContent = CSVManager.readContent(configFilePath);

			for (int i = 1; i < defaultValuesContent.length; i++) {
				String[] row = defaultValuesContent[i];

				MedicalTestMetaData medicalTestMetaData;
				
				if (Integer.parseInt(row[1]) == 0) {
					medicalTestMetaData =  new MedicalTestMetaData(row[0], row[2], Double.parseDouble(row[3]), Double.parseDouble(row[4]));
				} else {
					medicalTestMetaData =  new MedicalTestMetaData(row[0], row[2], Double.parseDouble(row[3]), Double.parseDouble(row[4]), Double.parseDouble(row[5]), Double.parseDouble(row[6]));
				}

				medicalTestMetaDataByNameMap.put(row[0], medicalTestMetaData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MedicalTestMetaData getMedicalTestMetaData(String name) {
		return medicalTestMetaDataByNameMap.get(name);
	}
	
	private void printMedicalTestDefaultValues() {
		
		System.out.println("Total default values : " + medicalTestMetaDataByNameMap.size());
		
		/*for(Map.Entry<String, MedicalTestMetaData> entry: medicalTestMetaDataByNameMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}*/
	}

	public static MedicalTestMetadataManager getInstance() {
		if (instance == null) {
			instance = new MedicalTestMetadataManager();
		}

		return instance;
	}

}
