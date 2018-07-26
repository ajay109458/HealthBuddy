package utility;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PdfToTextConverterPdfBox {

	public static void main(String[] args) throws InvalidPasswordException, IOException {
		 try (PDDocument document = PDDocument.load(new File("D:\\Hack\\MedicalReports\\Arjun.pdf"))) {

	            document.getClass();

	            if (!document.isEncrypted()) {
				
	                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	                stripper.setSortByPosition(true);

	                PDFTextStripper tStripper = new PDFTextStripper();

	                String pdfFileInText = tStripper.getText(document);
	                //System.out.println("Text:" + st);

					// split by whitespace
	                String lines[] = pdfFileInText.split("\\r?\\n");
	                for (String line : lines) {
	                    System.out.println(line);
	                }

	            }

	        }
	}
	
}
