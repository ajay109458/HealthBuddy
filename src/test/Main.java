package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import symptoms.SymptomsAnalyzer;

public class Main {

	public static void main(String[] args) throws IOException {

		List<String> symptoms = new ArrayList<>();
		
		symptoms.add("Fever");
		
		SymptomsAnalyzer.analyseSymptomsML(symptoms);
	}
}
