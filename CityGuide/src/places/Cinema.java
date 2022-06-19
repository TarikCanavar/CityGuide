package places;

import placeAttributes.Address;

public class Cinema extends Place{
    private boolean imax;
    public Cinema(String name, Address address, boolean imax) {
        super(name, address);
        this.imax = imax;
    }

    public boolean isImax() {
        return imax;
    }
}
