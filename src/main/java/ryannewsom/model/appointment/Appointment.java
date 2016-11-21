package ryannewsom.model.appointment;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ryannewsom.model.users.User;
import ryannewsom.model.users.entityinfo.Office;

import java.util.Date;

/**
 * Appointment
 */
@Document
public class Appointment {

    @Id
    private String id;
    public User user;
    public Date time;
    public Office office;

    public Appointment(User user, Date time, Office office){
        this.user = user;
        this.time = time;
        this.office = office;
    }

    public void setAppointmentId(final String id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "mId='" + id + '\'' +
                ", mUser=" + user +
                ", mTime=" + time +
                ", mOffice=" + office +
                '}';
    }
}
