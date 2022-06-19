package CityGuide;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import login.User;
import login.Admin;
import login.Login;
import login.LoginWindow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import places.Cafe;
import places.Cinema;
import places.Landscape;
import places.Museum;
import places.NightSpot;
import places.Place;
import places.Restaurant;
import placeAttributes.Address;
import placeAttributes.Comment;
import placeAttributes.PhoneNumber;

public class CityGuide {
	private HashMap<String, User> users;
	private HashMap<String, Admin> admins;
	private HashMap<String, ArrayList<Comment>> commentMap;
	private static CityGuide cityGuide;
	private Login currentUser;
	private HashMap<String, ArrayList<Place>> placeMap;
	private HashMap<Integer, Place> placeMapId;
	char valid = '\0';



	private CityGuide() {
		users = new HashMap<>();
		admins = new HashMap<>();
		commentMap = new HashMap<>();
		placeMap = new HashMap<>();
		placeMapId = new HashMap<>();
        placeMap.put("cafes",new ArrayList<>());
        placeMap.put("cinemas",new ArrayList<>()); 
        placeMap.put("landscapes",new ArrayList<>());
        placeMap.put("museums",new ArrayList<>());
        placeMap.put("nightspots",new ArrayList<>()); 
        placeMap.put("restaurants",new ArrayList<>());
		initialize();
		LoginWindow.main(null);
	}

	private void initialize() {
		JSONParser parser = new JSONParser();
		try {

			JSONObject adminsObject = (JSONObject) parser.parse(new FileReader("admins.json"));
			JSONArray adminsArray = (JSONArray) adminsObject.get("admins");
			Iterator<JSONObject> adminsIt = adminsArray.iterator();

			while (adminsIt.hasNext()) {
				JSONObject admin = adminsIt.next();
				this.admins.put((String) admin.get("email"),
						new Admin(admin.get("name"), admin.get("surname"), admin.get("email"), admin.get("password")));
			}
			File users = new File("users.json");
			if (users.exists()) {
				FileReader usersReader = new FileReader(users);
				JSONObject usersObject = (JSONObject) parser.parse(usersReader);
				JSONArray usersArray = (JSONArray) usersObject.get("users");
				Iterator<JSONObject> usersIt = usersArray.iterator();
				while (usersIt.hasNext()) {
					JSONObject user = usersIt.next();
					this.users.put((String) user.get("email"),
							new User(user.get("name"), user.get("surname"), user.get("email"), user.get("password")));
				}
			}
			JSONArray comments = JSONFileHandler.read("comments");
			Iterator<JSONObject> commentsIt = comments.iterator();
			while (commentsIt.hasNext()) {
				JSONObject comment = commentsIt.next();
				String name = (String) comment.get("name");
				String surname = (String) comment.get("surname");
				String mail = (String) comment.get("email");
				String place = (String) comment.get("place");
				String content = (String) comment.get("content");
				int rating = (int) (long) comment.get("rating");
				Comment comment1 = new Comment(mail, name, surname, rating, content, place);
				if (commentMap.containsKey(place)) {
					ArrayList<Comment> commentArrayList = commentMap.get(place);
					commentArrayList.add(comment1);
				} else {
					ArrayList<Comment> commentArrayList = new ArrayList<>();
					commentArrayList.add(comment1);
					commentMap.put(place, commentArrayList);
				}
			}
			JSONArray cafesFile = JSONFileHandler.read("cafes");
			Iterator<JSONObject> cafesIt = cafesFile.iterator();
			ArrayList<Place> cafes = placeMap.get("cafes");
			int i = 0;
			while (cafesIt.hasNext()) {
				JSONObject cafe = cafesIt.next();
				String city = (String) cafe.get("city");
				String name = (String) cafe.get("name");

				String district = (String) cafe.get("district");
				String neighborhood = (String) cafe.get("neighborhood");
				String street = (String) cafe.get("street");
				int doorNumber = (int) (long) cafe.get("doorNumber");
				Address address = new Address(city, district, neighborhood, street, doorNumber);
				int countryCode = (int) (long) cafe.get("countryCode");
				int code = (int) (long) cafe.get("code");
				int number = (int) (long) cafe.get("number");
				PhoneNumber phoneNumber = new PhoneNumber(countryCode, code, number);
				Cafe cafeToAdd = new Cafe(name, address, phoneNumber);
				cafeToAdd.setComments(commentMap.get(cafeToAdd.getName()));
				cafes.add(cafeToAdd);
				placeMapId.put(cafeToAdd.getID(), cafeToAdd);

			}
			JSONArray cinemasFile = JSONFileHandler.read("cinemas");
			Iterator<JSONObject> cinemasIt = cinemasFile.iterator();
			ArrayList<Place> cinemas = placeMap.get("cinemas");;
			while (cinemasIt.hasNext()) {
				JSONObject cinema = cinemasIt.next();
				String city = (String) cinema.get("city");
				String name = (String) cinema.get("name");

				String district = (String) cinema.get("district");
				String neighborhood = (String) cinema.get("neighborhood");
				String street = (String) cinema.get("street");
				int doorNumber = (int) (long) cinema.get("doorNumber");
				Address address = new Address(city, district, neighborhood, street, doorNumber);
				boolean imax = (boolean) cinema.get("imax");
				Cinema cinemaToAdd = new Cinema(name, address, imax);
				cinemaToAdd.setComments(commentMap.get(cinemaToAdd.getName()));
				cinemas.add(cinemaToAdd);
				placeMapId.put(cinemaToAdd.getID(), cinemaToAdd);
			}
			JSONArray landscapesFile = JSONFileHandler.read("landscapes");
			Iterator<JSONObject> landscapesIt = landscapesFile.iterator();
			ArrayList<Place> landscapes = placeMap.get("landscapes");
			while (landscapesIt.hasNext()) {
				JSONObject Landscape = landscapesIt.next();
				String city = (String) Landscape.get("city");
				String name = (String) Landscape.get("name");

				String district = (String) Landscape.get("district");
				String neighborhood = (String) Landscape.get("neighborhood");
				String street = (String) Landscape.get("street");
				int doorNumber = (int) (long) Landscape.get("doorNumber");
				Address address = new Address(city, district, neighborhood, street, doorNumber);
				String type = (String) Landscape.get("type");
				boolean hasFee = (boolean) Landscape.get("fee");
				Landscape landscapeToAdd = new Landscape(name, address, type, hasFee);
				landscapeToAdd.setComments(commentMap.get(landscapeToAdd.getName()));
				landscapes.add(landscapeToAdd);
				placeMapId.put(landscapeToAdd.getID(), landscapeToAdd);
			}
			JSONArray restaurantFile = JSONFileHandler.read("restaurants");
			Iterator<JSONObject> restaurantIt = restaurantFile.iterator();
			ArrayList<Place> restaurants = placeMap.get("restaurants");
			while (restaurantIt.hasNext()) {
				JSONObject cafe = restaurantIt.next();
				String city = (String) cafe.get("city");
				String name = (String) cafe.get("name"); 

				String district = (String) cafe.get("district");
				String neighborhood = (String) cafe.get("neighborhood");
				String street = (String) cafe.get("street");
				int doorNumber = (int) (long) cafe.get("doorNumber");
				Address address = new Address(city, district, neighborhood, street, doorNumber);
				Restaurant restaurantToAdd = new Restaurant(name, address);
				restaurantToAdd.setComments(commentMap.get(restaurantToAdd.getName()));

				restaurants.add(restaurantToAdd);
				placeMapId.put(restaurantToAdd.getID(), restaurantToAdd);
			}
			JSONArray museumsFile = JSONFileHandler.read("museums");
			Iterator<JSONObject> museumsIt = museumsFile.iterator();
			ArrayList<Place> museums = placeMap.get("museums");

			while (museumsIt.hasNext()) {
				JSONObject Museum = museumsIt.next();
				String city = (String) Museum.get("city");
				String name = (String) Museum.get("name");

				String district = (String) Museum.get("district");
				String neighborhood = (String) Museum.get("neighborhood");
				String street = (String) Museum.get("street");
				int doorNumber = (int) (long) Museum.get("doorNumber");
				Address address = new Address(city, district, neighborhood, street, doorNumber);
				String type = (String) Museum.get("type");
				Museum museumToAdd = new Museum(name, address, type);
				museumToAdd.setComments(commentMap.get(museumToAdd.getName()));
				museums.add(museumToAdd);
				placeMapId.put(museumToAdd.getID(), museumToAdd);
			}
			JSONArray nightSpotsFile = JSONFileHandler.read("nightspots");
			Iterator<JSONObject> nightSpotsIterator = nightSpotsFile.iterator();
			ArrayList<Place> nightSpots = placeMap.get("nightspots");

			while (nightSpotsIterator.hasNext()) {
				JSONObject nightSpot = nightSpotsIterator.next();
				String city = (String) nightSpot.get("city");
				String name = (String) nightSpot.get("name");

				String district = (String) nightSpot.get("district");
				String neighborhood = (String) nightSpot.get("neighborhood");
				String street = (String) nightSpot.get("street");
				int doorNumber = (int) (long) nightSpot.get("doorNumber");
				Address address = new Address(city, district, neighborhood, street, doorNumber);
				boolean alcohol = (boolean) nightSpot.get("alcohol");
				boolean liveMusic = (boolean) nightSpot.get("liveMusic");
				NightSpot nightSpotToAdd = new NightSpot(name, address, alcohol, liveMusic);
				nightSpotToAdd.setComments(commentMap.get(nightSpotToAdd.getName()));

				nightSpots.add(nightSpotToAdd);
				placeMapId.put(nightSpotToAdd.getID(), nightSpotToAdd);
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static CityGuide getInstance() {
		if (cityGuide == null) {
			cityGuide = new CityGuide();
		}
		return cityGuide;
	}

	public void deleteComment(String mail, Place place) {


        String placeName = place.getName();
        Comment commentToDelete = null;
        Iterator<Comment> comments = commentMap.get(placeName).iterator();
        while (comments.hasNext()) {
            Comment comment = comments.next();
            if (comment.getPlace().equals(placeName) && comment.getMailAddress().equals(mail)) {
                commentToDelete = comment;
                break;
            }
        }
        place.deleteComment(commentToDelete);
        //commentMap.get(placeName).remove(commentToDelete);
        Comment.deleteFromJSON(placeName, mail);
    }

	

	public HashMap<String, ArrayList<Place>> getPlaceMap() {
		return placeMap;
	}

	public char validateLogin(String email, String password) {

		if (admins.containsKey(email) && admins.get(email).getPassword().equals(password)) {
			valid = 'a';
			currentUser = admins.get(email);

		} else if (users.containsKey(email) && users.get(email).getPassword().equals(password)) {
			valid = 'u';
			currentUser = users.get(email);
		}
		return valid;
	}

	public boolean createUser(String name, String surname, String mailAddress, String password) {
		if (users.containsKey(mailAddress)) {
			return false;
		}
		boolean valid = User.createUser(name, surname, mailAddress, password);

		if (valid) {
			users.put(mailAddress, new User(name, surname, mailAddress, password));
		}
		return valid;
	}



	public ArrayList<Comment> getPlaceComments(String placeName) {
		return commentMap.get(placeName);
	}

	public boolean ifAdmin() {
		if (valid == 'a') {
			return true;
		} else
			return false;
	}

	public boolean addComment(String placeName, Comment comment, int ID) {
		if (commentMap.get(placeName) == null) {
			ArrayList<Comment> commentArrayList = new ArrayList<>();
			commentArrayList.add(comment);
			commentMap.put(placeName, commentArrayList);
		} else {
			ArrayList commentlist = commentMap.get(placeName);
			Iterator<Comment> comments = commentlist.iterator();
			Comment commentToIt;
			while (comments.hasNext()) {
				commentToIt = comments.next();
				if (commentToIt.getMailAddress().equals(currentUser.getMailAddress())) {
					return false;
				}
			}
			

		}
		placeMapId.get(ID).addComment(comment);
		comment.writeToJSON();
		return true;

	}
	public HashMap<Integer, Place> getPlaceMapId() {
		return placeMapId;
	}
	public Login getCurrentUser() {
		return currentUser;
	}
	public void deletePlace(String type, String placeName){


		ArrayList<Place> places = placeMap.get(type + "s");
		Place placeToDelete = null;
		Iterator<Place> placeIt = places.iterator();
		while (placeIt.hasNext()){
			Place place = placeIt.next();
			if(place.getName().equals(placeName)){
				placeToDelete = place;
				break;
			}
		}
		places.remove(placeToDelete);
		Place.deletePlace(type, placeName);
	}
}
