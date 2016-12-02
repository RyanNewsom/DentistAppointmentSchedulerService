package ryannewsom.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ryannewsom.model.appointment.Appointment;

/**
 * Provides Custom Mongo Specific Implementation. 
 */
@Repository
public class AppointmentCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Appointment getAppointmentAndDelete(String id) {
        return mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id).and("user").is(null)), Appointment.class);
    }
}
