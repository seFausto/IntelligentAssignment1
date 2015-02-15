import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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

}
