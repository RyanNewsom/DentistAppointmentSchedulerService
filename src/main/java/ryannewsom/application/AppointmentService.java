package ryannewsom.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ryannewsom.model.appointment.Appointment;
import ryannewsom.model.users.User;

import java.util.List;

/**
 *
 */
@Repository
public interface AppointmentService extends MongoRepository<Appointment, String> {
    public List<Appointment> findByUser(User user);
}
