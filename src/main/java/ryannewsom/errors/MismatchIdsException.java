package ryannewsom.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MismatchIdsException extends RuntimeException{
    public MismatchIdsException(){
        super("Appointment ID and the Appointment Object's id don't match");
    }
}
