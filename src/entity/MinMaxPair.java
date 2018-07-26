package entity;

import java.io.Serializable;

public class MinMaxPair implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	double min;
	double max;

	public MinMaxPair(double min, double max) {
		super();
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	@Override
	public String toString() {
		return "MinMaxPair [min=" + min + ", max=" + max + "]";
	}
}
