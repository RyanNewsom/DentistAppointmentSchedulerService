package ryannewsom.model.users;

import org.springframework.data.mongodb.core.mapping.Document;
import ryannewsom.model.users.entityinfo.ContactInfo;
import org.springframework.data.annotation.Id;


/**
 * User
 */
@Document
public class User {
    private String firstName;
    private String lastName;
    private ContactInfo contactInfo;

    public User(String firstName, String lastName, ContactInfo contactInfo){
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
