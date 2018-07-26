package constants;

public interface ReportAnalyzerConstants {
	
	/**
	 *  Regex to parse medical report
	 */
	public static String REMOVE_CONTENT_REGEX = "[Mm]ethod[ ]*:[A-MO-Za-z ./,-]*(5-P)*";
	public static String GET_REPORT_FIELD_REGEX = "\\*.[a-zA-Z ()0-9-]*[.]*\\d+";
	public static String GET_NUMBERIC_FIELD_VALUE_REGEX = "[0-9.-]+$";
	public static String REMOVE_NIL_VALUES_REGEX = "Nil.*$";
	public static String GET_USER_NAME_REGEX = "Name[ ]*:[A-Za-z ]*[0-9]*";
	public static String GET_USER_AGE_SEX_REGEX = "Age\\/Sex[ ]*:[ 0-9A-Za-z].*?MALE";
	
	
	/**
	 * Configuration files
	 */
	public static String CONFIG_FOLDER = "D:\\Hack\\config\\";
	public static String MEDICAL_TEST_DEFAULT_VALUES_CONFIG = "medicalTestDefaultValue.csv";
	public static String MEDICAL_TEST_SUGESSTION = "medicalTestSuggestions.csv";
}
