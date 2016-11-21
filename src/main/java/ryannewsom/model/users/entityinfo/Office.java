package ryannewsom.model.users.entityinfo;

import org.springframework.data.annotation.Id;

/**
 * Office
 */
public class Office {
    @Id
    private String id;
    private ContactInfo contactInfo;

    public Office(ContactInfo contactInfo){
        this.contactInfo = contactInfo;
    }

    public void setOfficeId(String id) {
        this.id = id;
    }

    public String getOfficeId() {
        return id;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id='" + id + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
