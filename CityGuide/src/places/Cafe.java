package places;

import placeAttributes.Address;
import placeAttributes.PhoneNumber;

public class Cafe extends Place{
    private PhoneNumber phoneNumber;
    public Cafe(String name, Address address, PhoneNumber phoneNumber) {
        super(name, address);
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber; 
    }
}
