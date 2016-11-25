package ryannewsom.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error describing the situation where an Appointment has already been scheduled by another user. Therefore, it is
 * no longer available
 */
@ResponseStatus(HttpStatus.GONE)
public class AppointmentAlreadyScheduledException extends RuntimeException{

    /**
     * Constructor
     */
    public AppointmentAlreadyScheduledException(){
        super("Appointment no longer available");
    }
}
