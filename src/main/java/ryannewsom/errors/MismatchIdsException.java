package ryannewsom.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error when the id mapped to is not the same id as the
 * json body passed in
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MismatchIdsException extends RuntimeException{

    /**
     * Constructor
     */
    public MismatchIdsException(){
        super("Appointment ID and the Appointment Object's id don't match");
    }
}
