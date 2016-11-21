package ryannewsom.model.users.entityinfo;


import org.springframework.data.annotation.Id;

/**
 * Contact Info
 */
public class ContactInfo {
    @Id
    private int id;
    private String cellNumber;
    private PhysicalAddress physicalAddress;

    public ContactInfo(String cellNumber, PhysicalAddress physicalAddress){
        this.cellNumber = cellNumber;
        this.physicalAddress = physicalAddress;
    }
}
