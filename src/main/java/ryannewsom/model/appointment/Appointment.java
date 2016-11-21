package ryannewsom.model.appointment;


import org.springframework.data.annotation.Id;
import ryannewsom.model.users.Patient;
import ryannewsom.model.users.entityinfo.Office;

import java.util.Date;

/**
 * Appointment
 */
public class Appointment {

    @Id
    public int id;
    public Patient patient;
    public Date time;
    public Office office;

    public Appointment(Patient patient, Date time, Office office){
        this.patient = patient;
        this.time = time;
        this.office = office;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "mId='" + id + '\'' +
                ", mPatient=" + patient +
                ", mTime=" + time +
                ", mOffice=" + office +
                '}';
    }
}
