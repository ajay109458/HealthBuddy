package entity;

import java.io.Serializable;

public class MedicalTestSuggestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String description;

	String lowResult;

	String highResult;

	String lowSuggestion;

	String highSuggestion;

	public MedicalTestSuggestion(String description, String lowResult, String highResult, String lowSuggestion,
			String highSuggestion) {
		super();
		this.description = description;
		this.lowResult = lowResult;
		this.highResult = highResult;
		this.lowSuggestion = lowSuggestion;
		this.highSuggestion = highSuggestion;
	}

	public String getDescription() {
		return description;
	}

	public String getLowResult() {
		return lowResult;
	}

	public String getHighResult() {
		return highResult;
	}

	public String getLowSuggestion() {
		return lowSuggestion;
	}

	public String getHighSuggestion() {
		return highSuggestion;
	}

}