package placeAttributes;

import login.Login;
import login.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import places.Place;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

public class Comment {
	String mailAddress;
	private String name;
	private String surname;
	private int rating;
	private String content;
	private String place;

	public Comment(String mailAddress, String name, String surname, int rating, String content, String place) {
		this.mailAddress = mailAddress;
		this.name = name;
		this.surname = surname;
		this.rating = rating;
		this.content = content;
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getSurname() {
		return surname;
	}

	public int getRating() {
		return rating;
	}

	public String getRateAsString() {
		return String.valueOf(rating);
	}

	public String getPlace() {
		return place;
	}

	public String getContent() {
		return content;
	}
	
	public static void deleteFromJSON(String place, String mailAddress){
        File comments = new File("comments.json");
        JSONParser parser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("comments.json"));
            JSONArray commentList = (JSONArray) jsonObject.get("comments");
            JSONArray newCommentList = new JSONArray();
            Iterator<JSONObject> commentIt = commentList.iterator();
            while (commentIt.hasNext()){
                JSONObject comment = commentIt.next();
                if(!comment.get("place").equals(place) || !comment.get("email").equals(mailAddress)){
                    newCommentList.add(comment);
                }
            }

            JSONObject newRecords = new JSONObject();
            newRecords.put("comments", newCommentList);
            try (FileWriter file = new FileWriter("comments.json")) {
                file.write(newRecords.toString());
                file.flush();

            } catch (Exception e1) {
                System.out.println(e1);
            }

        } catch (Exception e1) {

            e1.printStackTrace();
        }
    }

	public void writeToJSON() {
		File comments = new File("comments.json");
		if (comments.exists()) {

			JSONParser parser = new JSONParser(); 
			try {

				JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("comments.json"));
				JSONArray commentList = (JSONArray) jsonObject.get("comments");

				JSONObject commentAttributes = new JSONObject();
				commentAttributes.put("email", mailAddress);
				commentAttributes.put("name", name);
				commentAttributes.put("surname", surname);

				commentAttributes.put("rating", rating);
				commentAttributes.put("content", content);
				commentAttributes.put("place", place);

				commentList.add(commentAttributes);
				JSONObject newRecords = new JSONObject();
				newRecords.put("comments", commentList);
				try (FileWriter file = new FileWriter("comments.json")) {
					file.write(newRecords.toString());
					file.flush();

				} catch (Exception e1) {
					System.out.println(e1);
				}

			} catch (Exception e1) {

				e1.printStackTrace();
			}
		} else {
			// TODO validation
			JSONArray jsonArray = new JSONArray();

			JSONObject commentAttributes = new JSONObject();
			commentAttributes.put("email", mailAddress);
			commentAttributes.put("name", name);
			commentAttributes.put("surname", surname);
			commentAttributes.put("rating", rating);
			commentAttributes.put("content", content);
			commentAttributes.put("place", place);

			JSONObject userAttributes2 = new JSONObject();
			userAttributes2.put("comments", jsonArray);

			jsonArray.add(commentAttributes);

			try (FileWriter file = new FileWriter("comments.json")) {
				file.write(userAttributes2.toString());
				file.flush();

			} catch (Exception e1) {
				System.out.println(e1);

			}

		}
	}
}