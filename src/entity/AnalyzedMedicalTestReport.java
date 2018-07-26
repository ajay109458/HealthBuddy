package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AnalyzedMedicalTestReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	User user;

	List<MedicalTest> abnormalMedicalTests;
	
	int totalTests = 0;

	public AnalyzedMedicalTestReport(User user) {
		super();
		this.user = user;
		this.abnormalMedicalTests = new ArrayList<>();
	}

	public User getUser() {
		return user;
	}

	public void addMedicalTest(MedicalTest medicalTest) {
		this.abnormalMedicalTests.add(medicalTest);
	}
	
	public List<MedicalTest> getAbnormalMedicalTests() {
		return abnormalMedicalTests;
	}

	public void setAbnormalMedicalTests(List<MedicalTest> abnormalMedicalTests) {
		this.abnormalMedicalTests = abnormalMedicalTests;
	}
	
	public int getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
	}

	@Override
	public String toString() {
		return "AnalyzedMedicalTestReport [user=" + user + ", abnormalMedicalTests=" + abnormalMedicalTests + "]";
	}
	
}
