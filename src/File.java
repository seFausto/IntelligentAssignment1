import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class File {

	public static void WriteFile(String filename, String fileContent) {
		try {

			PrintWriter file = new PrintWriter(new FileOutputStream(filename,
					false));

			file.print(fileContent);
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String[] ReadFile(String filename) throws IOException {

		List<String> result = new ArrayList<String>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				filename));

		String line;

		while ((line = bufferedReader.readLine()) != null) {
			result.add(line);
		}

		return result.toArray(new String[result.size()]);
	}

}
