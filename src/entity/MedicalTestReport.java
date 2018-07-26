package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MedicalTestReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	User user;

	List<MedicalTest> medicalTests;

	public MedicalTestReport(User user) {
		super();
		this.user = user;
		this.medicalTests = new ArrayList<>();
	}

	public User getUser() {
		return user;
	}

	public void addMedicalTest(MedicalTest medicalTest) {
		this.medicalTests.add(medicalTest);
	}

	public List<MedicalTest> getMedicalTests() {
		return this.medicalTests;
	}
}
