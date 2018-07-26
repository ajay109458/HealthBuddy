package report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import constants.Genders;
import constants.ReportAnalyzerConstants;
import entity.AnalyzedMedicalTestReport;
import entity.AnalyzedMedicalTest;
import entity.MedicalTest;
import entity.MedicalTestMetaData;
import entity.MedicalTestReport;
import entity.MedicalTestSuggestion;
import entity.MinMaxPair;
import entity.User;
import utility.FileManager;
import utility.PdfToTextConverterExternal;
import utility.PdfToTextTess4J;
import utility.RegexManager;

public class MedicalReportAnalyzer {

	public static MedicalTestReport getMedicalTestReport(String reportPath) {
		
		List<String> medicalTestMatchingStrings;
		User user;
		int count = 1;
		String content  = PdfToTextConverterExternal.getPdfText(reportPath);
		MedicalTestReport medicalTestReport;
		while(true) {
			content = RegexManager.removeMatchingPattern(content, ReportAnalyzerConstants.REMOVE_CONTENT_REGEX);
			
			/*
			 * Fetch age and sex from report
			 */
			user = getUser(content);
			medicalTestReport = new MedicalTestReport(user);
			
			/*
			 * Fetch medical tests
			 */
			medicalTestMatchingStrings = RegexManager.getMatchingString(content,
					ReportAnalyzerConstants.GET_REPORT_FIELD_REGEX);
			
			if(count == 1 && (medicalTestMatchingStrings.size() == 0 || user.getUserName() == null || user.getUserName() == "")) {
				content = PdfToTextTess4J.getFileContent(reportPath);
				count++;
			} else {
				break;
			}
		}
		
		
		
		for (String matchString : medicalTestMatchingStrings) {

			matchString = RegexManager.removeMatchingPattern(matchString,
					ReportAnalyzerConstants.REMOVE_NIL_VALUES_REGEX);
			List<String> observedValues = RegexManager.getMatchingString(matchString,
					ReportAnalyzerConstants.GET_NUMBERIC_FIELD_VALUE_REGEX);
			String observedValueString = (observedValues.size() > 0) ? observedValues.get(0) : "";
			String testName = RegexManager
					.removeMatchingPattern(matchString, ReportAnalyzerConstants.GET_NUMBERIC_FIELD_VALUE_REGEX).trim()
					.substring(1);

			medicalTestReport.addMedicalTest(new MedicalTest(testName, parseMedicalTestValue(observedValueString)));
		}

		return medicalTestReport;
	}

	public static AnalyzedMedicalTestReport getAnalysedMedicalReport(String filePath) {
		MedicalTestReport medicalTestReport = MedicalReportAnalyzer.getMedicalTestReport(filePath);
		User user = medicalTestReport.getUser();
		AnalyzedMedicalTestReport analyzedMedicalReport = new AnalyzedMedicalTestReport(user);
		
		analyzedMedicalReport.setTotalTests(medicalTestReport.getMedicalTests().size());
		
		for (MedicalTest medicalTest : medicalTestReport.getMedicalTests()) {

			MedicalTestMetaData medicalTestMetaData = MedicalTestMetadataManager.getInstance()
					.getMedicalTestMetaData(medicalTest.getName());

			if (medicalTestMetaData == null) {
				System.out.println("Default values not found for : " + medicalTest.getName());
				continue;
			}

			MinMaxPair medicalTestMinMaxValue;
			if (!medicalTestMetaData.isGenderSpecific()) {
				medicalTestMinMaxValue = medicalTestMetaData.getMedicalTestMinMaxValue(Genders.ALL);
			} else {
				Genders gender = (user.getIsUserMale()) ? Genders.MALE : Genders.FEMALE;
				medicalTestMinMaxValue = medicalTestMetaData.getMedicalTestMinMaxValue(gender);
			}

			if (medicalTestMinMaxValue != null && medicalTest.getObservedValue() != 0) {

				if (medicalTest.getObservedValue() < medicalTestMinMaxValue.getMin()
						|| medicalTest.getObservedValue() > medicalTestMinMaxValue.getMax()) {

					AnalyzedMedicalTest analyzedMedicalTest = new AnalyzedMedicalTest(medicalTest);
					
					MedicalTestSuggestion medicalTestSuggestion = MedicalTestSuggestionManager.getInstance().getMedicalTestSuggestion(medicalTest.getName());
					
					analyzedMedicalTest.setSuggestion(medicalTestSuggestion);
					analyzedMedicalTest.setUnit(medicalTestMetaData.getUnit());
					analyzedMedicalTest.setMinValue(medicalTestMinMaxValue.getMin());
					analyzedMedicalTest.setMaxValue(medicalTestMinMaxValue.getMax());

					analyzedMedicalTest.setDangerPercentage(dangerPercentage(medicalTest.getObservedValue(),
							analyzedMedicalTest.getMinValue(), analyzedMedicalTest.getMaxValue()));

					analyzedMedicalReport.addMedicalTest(analyzedMedicalTest);
				}
			}
		}

		return analyzedMedicalReport;
	}
	
	private static String parseName(String content) {
		List<String> nameMatchingStrings = RegexManager.getMatchingString(content,
				ReportAnalyzerConstants.GET_USER_NAME_REGEX);

		if (nameMatchingStrings.size() == 0)
			return null;

		String nameString = nameMatchingStrings.get(0);
		return trimExtraFields(RegexManager
				.removeMatchingPattern(nameString.split(":")[1], ReportAnalyzerConstants.GET_NUMBERIC_FIELD_VALUE_REGEX)
				.trim());
	}
	
	private static String trimExtraFields(String result) {
		return result.replace("Registration Number", "").trim();
	}

	private static double dangerPercentage(double value, double min, double max) {

		double base = (value < min) ? min : max;

		return ((value - base) * 100) / base;
	}

	private static double parseMedicalTestValue(String observedValueString) {
		double result = 0;

		try {
			if (observedValueString.contains("-")) {
				String[] stringValues = observedValueString.split("-");
				if (stringValues.length > 1) {
					double firstValue = Double.parseDouble(stringValues[0]);
					double secondValue = Double.parseDouble(stringValues[1]);

					result = (firstValue + secondValue) / 2;
				}
			} else {
				result = Double.parseDouble(observedValueString);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return result;
	}
	
	private static User getUser(String content) {
		List<String> ageSexMatchingStrings = RegexManager.getMatchingString(content,
				ReportAnalyzerConstants.GET_USER_AGE_SEX_REGEX);
		int age = 0;
		boolean isMale = false;

		try {
			if (ageSexMatchingStrings.size() != 0) {
				String ageSexMatchingString = ageSexMatchingStrings.get(0);
				String[] ageSexValues = ageSexMatchingString.split("/|:");
				if (ageSexValues.length > 2) {
					String ageString = ageSexValues[2].trim().split(" ")[0].trim();					
					age = Integer.parseInt(ageString);
					isMale = ageSexValues[3].trim().equals("MALE");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new User(parseName(content), age, isMale);
	}

}
