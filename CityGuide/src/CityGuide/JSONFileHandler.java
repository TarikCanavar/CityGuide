package CityGuide;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class JSONFileHandler {
	public JSONFileHandler() {

	}

	public static JSONArray read(String fileName) {
		JSONArray fileArray = null;
		File users = new File(fileName + ".json");
		if (users.exists()) {

			try {
				JSONParser parser = new JSONParser();
				FileReader fileReader = new FileReader(users);
				JSONObject fileObject = (JSONObject) parser.parse(fileReader);
				fileArray = (JSONArray) fileObject.get(fileName);

			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}

		}
		return fileArray;
	}
}
