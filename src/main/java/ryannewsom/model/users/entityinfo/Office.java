package ryannewsom.model.users.entityinfo;

import org.springframework.data.annotation.Id;

/**
 * Office
 */
public class Office {
    @Id
    private int id;
    private ContactInfo contactInfo;
}
