package places;

import placeAttributes.Address;

public class Landscape extends Place{
    public String type;
    private boolean fee;
    public Landscape(String name, Address address,String type, boolean fee) {
        super(name, address);
        this.type = type;
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public boolean hasFee() {
        return fee;
    }
}
