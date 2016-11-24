package ryannewsom.model.users.entityinfo;

import org.springframework.data.annotation.Id;

/**
 * Physical Address
 */
public class PhysicalAddress {
    private int streetNumber;
    private String street;
    private int zipCode;
    private String city;
    private String state;

    public PhysicalAddress(){

    }

    public PhysicalAddress(int streetNumber, String street, String city, String state, int zipCode){
        this.streetNumber = streetNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }
}
