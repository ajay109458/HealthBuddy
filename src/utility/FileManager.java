package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileManager {

	public static void writeTextFile(String filePath, String content) throws IOException {
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(content);
		fileWriter.close();
	}

	public static String readTextFile(String filePath) {

		String result = "";

		try (FileReader reader = new FileReader(filePath);
				BufferedReader bufferedReader = new BufferedReader(reader);) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static void writeBinaryFile(String filePath, InputStream uploadedInputStream) {
		try {
			FileOutputStream out = new FileOutputStream(new File(filePath));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(filePath));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
