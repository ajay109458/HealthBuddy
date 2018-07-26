package entity;

public class AnalyzedMedicalTest extends MedicalTest{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    double minValue;
	
	double maxValue;
	
	double dangerPercentage;

	String unit;
	
	MedicalTestSuggestion suggestion;

	public AnalyzedMedicalTest(MedicalTest medicalTest) {
		super(medicalTest);
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public MedicalTestSuggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(MedicalTestSuggestion suggestion) {
		this.suggestion = suggestion;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getDangerPercentage() {
		return dangerPercentage;
	}

	public void setDangerPercentage(double dangerPercentage) {
		this.dangerPercentage = dangerPercentage;
	}

	@Override
	public String toString() {
		return "AnalyzedMedicalTest [minValue=" + minValue + ", maxValue=" + maxValue + ", dangerPercentage="
				+ dangerPercentage + ", unit=" + unit + ", suggestion=" + suggestion + ", name=" + name
				+ ", observedValue=" + observedValue + "]";
	}
}
