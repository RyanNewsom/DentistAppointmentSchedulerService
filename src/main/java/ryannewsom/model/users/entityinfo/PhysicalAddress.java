package ryannewsom.model.users.entityinfo;

import org.springframework.data.annotation.Id;

/**
 * Physical Address of an entity
 */
public class PhysicalAddress {
    private int streetNumber;
    private String street;
    private int zipCode;
    private String city;
    private String state;

    /**
     * Constructor
     */
    public PhysicalAddress(){

    }

    /**
     * Constructor
     * @param streetNumber - the street number
     * @param street - the street name
     * @param city - the city
     * @param state - the state
     * @param zipCode- the zip code
     */
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
