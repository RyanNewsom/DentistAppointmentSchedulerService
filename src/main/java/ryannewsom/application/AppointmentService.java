package ryannewsom.application;

import org.springframework.data.mongodb.repository.MongoRepository;
import ryannewsom.model.appointment.Appointment;

/**
 *
 */
public interface AppointmentService extends MongoRepository<Appointment, String>{

}
