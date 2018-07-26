package entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import constants.Genders;

public class MedicalTestMetaData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String name;
	
	String unit;
	
	boolean isGenderSpecific;
	
	Map<Genders, MinMaxPair> minMaxValueByKeyMap;

	private MedicalTestMetaData() {
		super();
		minMaxValueByKeyMap = new HashMap<>();
	}
	
	public MedicalTestMetaData(String name, String unit, double min, double max) {
		this();
		
		this.name = name;
		this.unit = unit;
		this.minMaxValueByKeyMap.put(Genders.ALL, new MinMaxPair(min, max));
	}
	
	public MedicalTestMetaData(String name, String unit, double minM, double maxM, double minF, double maxF) {
		this();
		
		this.isGenderSpecific = true;
		this.name = name;
		this.unit = unit;
		this.minMaxValueByKeyMap.put(Genders.MALE, new MinMaxPair(minM, maxM));
		this.minMaxValueByKeyMap.put(Genders.FEMALE, new MinMaxPair(minF, maxF));
	}

	@Override
	public String toString() {
		return "MedicalTestMetaData [name=" + name + ", unit=" + unit + ", isGenderSpecific=" + isGenderSpecific
				+ ", minMaxValueByKeyMap=" + minMaxValueByKeyMap + "]";
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public boolean isGenderSpecific() {
		return isGenderSpecific;
	}

	public MinMaxPair getMedicalTestMinMaxValue(Genders gender) {
		return minMaxValueByKeyMap.get(gender);
	}
}
