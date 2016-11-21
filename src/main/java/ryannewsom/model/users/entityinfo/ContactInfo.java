package ryannewsom.model.users.entityinfo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Contact Info
 */
@Document
public class ContactInfo {
    private String cellNumber;
    private PhysicalAddress physicalAddress;

    public ContactInfo(String cellNumber, PhysicalAddress physicalAddress){
        this.cellNumber = cellNumber;
        this.physicalAddress = physicalAddress;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "cellNumber='" + cellNumber + '\'' +
                ", physicalAddress=" + physicalAddress +
                '}';
    }
}
