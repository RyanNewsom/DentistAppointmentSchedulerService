package ryannewsom.model.users;

import ryannewsom.model.users.entityinfo.ContactInfo;
import org.springframework.data.annotation.Id;

/**
 * User
 */
public class User {
    @Id
    protected int id;
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
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
