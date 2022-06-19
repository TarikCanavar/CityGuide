package placeAttributes;
public class Address {
    private String city;
    private String district;
    private String neighborhood;
    private String street;
    private int doorNumber;

    public Address(String city, String district, String neighborhood, String street, int doorNumber) {
        this.city = city;
        this.district = district;
        this.neighborhood = neighborhood;
        this.street = street;
        this.doorNumber = doorNumber;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public int getDoorNumber() {
        return doorNumber;
    }
    @Override
    public String toString() {
        return city+" "+district+" "+neighborhood+" "+street+" "+doorNumber;
    }
}
