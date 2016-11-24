package ryannewsom.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 */
@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AppointmentAlreadyScheduledException extends RuntimeException{
    public AppointmentAlreadyScheduledException(){
        super("Appointment no longer available");
    }
}
