package places;

import placeAttributes.Address;

public class Museum extends Place{
    private String type;
    public Museum(String name, Address address, String type) {
        super(name, address);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
