package report;

import java.util.HashMap;
import java.util.Map;

import constants.ReportAnalyzerConstants;
import entity.MedicalTestMetaData;
import entity.MedicalTestSuggestion;
import utility.CSVManager;

public class MedicalTestSuggestionManager {

	private static MedicalTestSuggestionManager instance;

	private Map<String, MedicalTestSuggestion> medicalTestSuggestionByNameMap;

	private MedicalTestSuggestionManager() {
		super();

		medicalTestSuggestionByNameMap = new HashMap<>();

		populateMedicalTestsSuggestion();
	}

	private void populateMedicalTestsSuggestion() {

		String configFilePath = ReportAnalyzerConstants.CONFIG_FOLDER + ReportAnalyzerConstants.MEDICAL_TEST_SUGESSTION;
		try {
			String[][] suggestions = CSVManager.readContent(configFilePath);

			for (int i = 1; i < suggestions.length; i++) {
				String[] row = suggestions[i];

				MedicalTestSuggestion medicalTestSuggestion = new MedicalTestSuggestion(parseSuggestionMessage(row[1]), parseSuggestionMessage(row[2]), parseSuggestionMessage(row[3]), parseSuggestionMessage(row[4]),
						parseSuggestionMessage(row[5]));

				medicalTestSuggestionByNameMap.put(row[0], medicalTestSuggestion);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MedicalTestSuggestion getMedicalTestSuggestion(String name) {
		return medicalTestSuggestionByNameMap.get(name);
	}

	public String parseSuggestionMessage(String suggestionMessage) {
		String result = "";
		if (suggestionMessage != null) {
			String[] messages = suggestionMessage.split("-");
			for (String message : messages) {
				result += message + " ";
			}
		}
		return result;
	}

	public static MedicalTestSuggestionManager getInstance() {
		if (instance == null) {
			instance = new MedicalTestSuggestionManager();
		}

		return instance;
	}

}
