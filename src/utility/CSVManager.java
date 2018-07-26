package utility;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVManager {

	public static String[][] readContent(String filePath) throws IOException {
		List<String[]> result = new ArrayList<String[]>();

		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
				CSVReader csvReader = new CSVReader(reader);) {
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				result.add(nextRecord);
			}
		}

		return result.toArray(new String[result.size()][]);
	}

	public static void writeContent(String filePath, String[][] content) throws IOException {

		try (Writer writer = Files.newBufferedWriter(Paths.get(filePath));
				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			
			for(int i=0; i < content.length; i++) {
				csvWriter.writeNext(content[i]);
			}
		}
	}

}
