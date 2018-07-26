package entity;

import java.io.Serializable;

public class AnalyzedDisease implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String name;

	double occurancePercentage;

	public AnalyzedDisease(String name, double occurancePercentage) {
		super();
		this.name = name;
		this.occurancePercentage = occurancePercentage;
	}

	public String getName() {
		return name;
	}

	public double getOccurancePercentage() {
		return occurancePercentage;
	}

}
