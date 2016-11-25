package ryannewsom.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error indicating an Appointment was not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppointmentNotFoundException extends RuntimeException {

    /**
     * Constructor
     * @param id - appointmentid that was not found
     */
    public AppointmentNotFoundException(String id) {
        super("could not find appointment:" + id);
    }
}
