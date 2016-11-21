package ryannewsom.model.users;

import ryannewsom.model.appointment.Appointment;
import ryannewsom.model.users.entityinfo.ContactInfo;

import java.util.Collection;

/**
 * Patient
 */
public class Patient extends User {
    public Patient(String firstName, String lastName, ContactInfo contactInfo){
        super(firstName, lastName, contactInfo);
    }


}
