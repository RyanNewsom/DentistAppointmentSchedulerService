package ryannewsom.model.users;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import ryannewsom.model.users.entityinfo.ContactInfo;
import org.springframework.data.annotation.Id;


/**
 * User
 */
@Document
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private ContactInfo contactInfo;

    public User(){
        
    }

    public User(String firstName, String lastName, ContactInfo contactInfo){
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
        this.userId = ObjectId.get().toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId() {
        ObjectId.get().toString();;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", id='" + userId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
