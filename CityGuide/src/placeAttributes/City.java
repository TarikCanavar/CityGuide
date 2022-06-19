package placeAttributes;
import java.util.ArrayList;

import places.Place;
public class City {
    private final ArrayList<Place> places = new ArrayList<>();
    private String name;
    private int ID;

    public City(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }
}
