package ryannewsom.model.users.entityinfo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Contact Info for an entity
 */
@Document
public class ContactInfo {
    private String cellNumber;
    private PhysicalAddress physicalAddress;

    /**
     * Constructor
     */
    public ContactInfo() {

    }

    /**
     * Constructor
     * @param cellNumber - contact number
     * @param physicalAddress - address for entity
     */
    public ContactInfo(String cellNumber, PhysicalAddress physicalAddress){
        this.cellNumber = cellNumber;
        this.physicalAddress = physicalAddress;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public PhysicalAddress getPhysicalAddress() {
        return physicalAddress;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "cellNumber='" + cellNumber + '\'' +
                ", physicalAddress=" + physicalAddress +
                '}';
    }
}
