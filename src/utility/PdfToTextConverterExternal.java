package utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PdfToTextConverterExternal {
	
	public static String getPdfText(String filePath) {
		
		String result = null;
		
		try {
			ProcessBuilder ps = new ProcessBuilder( "cmd.exe", "/c", "pdf2txt.py \"" + filePath + "\"");
			ps.redirectErrorStream(true);
			
			Process pr = ps.start();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			String finalLine = "";
			while ((line = in.readLine()) != null) {
			    finalLine += line;
			}
			pr.waitFor();
			
			result = finalLine;
		} catch (IOException | InterruptedException e) {
			System.out.println("Unable to parse report : " + filePath);
			e.printStackTrace();
		}
		
		return result;
	}

}
