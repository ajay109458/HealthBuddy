package entity;

import java.io.Serializable;

public class MedicalTest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	double observedValue;

	public MedicalTest(String name, double observedValue) {
		super();
		this.name = name;
		this.observedValue = observedValue;
	}
	
	public MedicalTest(MedicalTest medicalTest) {
		super();
		this.name = medicalTest.name;
		this.observedValue = medicalTest.observedValue;
	}

	public String getName() {
		return name;
	}

	public double getObservedValue() {
		return observedValue;
	}

	@Override
	public String toString() {
		return "MedicalTest [name=" + name + ", observedValue=" + observedValue + "]";
	}
}
