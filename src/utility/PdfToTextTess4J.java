package utility;

import java.io.File;
import java.io.IOException;

import constants.ReportAnalyzerConstants;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class PdfToTextTess4J {
	public static String getFileContent(String filePath) {
		File imageFile = new File(filePath);
		//Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		instance.setDatapath(ReportAnalyzerConstants.CONFIG_FOLDER + "tessdata");

		String result = "";
		try {
			result = instance.doOCR(imageFile);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public static void main(String[] args) {
		String content = getFileContent("D:\\Hack\\MedicalReports\\Arjun.pdf");
		
		try {
			FileManager.writeTextFile("D:\\Hack\\tmp\\ocrContent.txt", content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
