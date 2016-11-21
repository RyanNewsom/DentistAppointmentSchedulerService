package ryannewsom.model.users.entityinfo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Contact Info
 */
@Document
public class ContactInfo {
    @Id
    private String id;
    private String cellNumber;
    private PhysicalAddress physicalAddress;

    public ContactInfo(String cellNumber, PhysicalAddress physicalAddress){
        this.cellNumber = cellNumber;
        this.physicalAddress = physicalAddress;
    }

    public void setContactInfoId(String id) {
        this.id = id;
    }

    public String getGontactInfoId(){
        return id;
    }
}
