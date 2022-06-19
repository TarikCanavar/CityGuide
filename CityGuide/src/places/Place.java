package places;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import placeAttributes.Address;
import placeAttributes.Comment;
public abstract class Place {
    private String name;
    private ArrayList<Comment> comments;
    private Address address;
    private static int IDcounter = 0;
    private int ID;
    private float averageRating;

    public int getID() {
        return ID;
    }

    public Place(String name, Address address) {
        this.name = name;
        this.address = address;
        ID = IDcounter;
        IDcounter++; 
    } 

    public String getName() { 
        return name;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Address getAddress() {
        return address;
    }
    public float getRating() {
        return averageRating;
    }
    @Override
    public String toString() {
        return String.valueOf(averageRating);
    }

    public void addComment(Comment comment){
        if(comments != null){
            int total = (int) (comments.size() * averageRating);
            comments.add(comment);
            total += comment.getRating();          
            averageRating = (float) total / comments.size();
          
        }
        else{
            comments = new ArrayList<>();
            comments.add(comment);
            averageRating = comment.getRating();
        }
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
        if(comments != null){
            Iterator<Comment> cIt = comments.iterator();
            int totalRating = 0;
            while (cIt.hasNext()){
                Comment c = cIt.next();
                totalRating += c.getRating();
            }
            averageRating = (float) totalRating / comments.size();
        }

    }

    public static void deletePlace(String type, String placeName){
        File comments = new File(type + "s.json");
        JSONParser parser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(type + "s.json"));
            JSONArray placeList = (JSONArray) jsonObject.get(type + "s");
            JSONArray newPlaceList = new JSONArray();
            Iterator<JSONObject> commentIt = placeList.iterator();
            while (commentIt.hasNext()){
                JSONObject place = commentIt.next();
                if(!place.get("name").equals(placeName)){
                    newPlaceList.add(place);
                }
            }

            JSONObject newRecords = new JSONObject();
            newRecords.put(type + "s", newPlaceList);
            try (FileWriter file = new FileWriter(type + "s.json")) {
                file.write(newRecords.toString());
                file.flush();

            } catch (Exception e1) {
                System.out.println(e1);
            }

        } catch (Exception e1) {

            e1.printStackTrace();
        }
    }
    public void deleteComment(Comment comment){
    	if(comments.size() > 1) {
    		 int totalRating = (int) (averageRating * comments.size());
    	        totalRating -= comment.getRating(); 	
    	        comments.remove(comment); 
    	        averageRating = (float) totalRating / (float) comments.size();
    	        
    	}
    	else {
    		averageRating = 0;
    		comments.remove(comment);
    	}
       
    }

}