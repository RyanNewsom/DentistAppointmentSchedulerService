package ryannewsom.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ryannewsom.errors.AppointmentAlreadyScheduledException;
import ryannewsom.errors.AppointmentNotFoundException;
import ryannewsom.errors.MismatchIdsException;
import ryannewsom.model.appointment.Appointment;
import ryannewsom.model.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A REST controller to handle Dentist Appointment CRUD
 */
@RestController
public class DentistAppointmentRestController  {
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Server Online";
    }

    @RequestMapping(value = "/Appointment", method = RequestMethod.GET)
    @ResponseBody
    List<Appointment> getAvailableAppointments() {
        return appointmentService.findByUser(null);
    }

    @RequestMapping(value = "/Appointment/{appointmentId}", method = RequestMethod.POST)
    @ResponseBody
    void createAppointment(@PathVariable String appointmentId, @RequestBody Appointment appointment) {
        validateAppointment(appointmentId);

        if(appointmentId.equals(appointment.getAppointmentId())) {
            Appointment unscheduledAppointment = appointmentService.findOne(appointmentId);

            if (unscheduledAppointment.getUser() == null) {
                User patient = appointment.getUser();
                patient.setUserId();
                unscheduledAppointment.setUser(patient);
                appointmentService.save(unscheduledAppointment);
            } else {
                throw new AppointmentAlreadyScheduledException();
            }
        } else{
            throw new MismatchIdsException();
        }
    }

    @RequestMapping(value = "/Appointment/{appointmentId}", method = RequestMethod.GET)
    @ResponseBody
    Appointment getAppointmentById(@PathVariable String appointmentId) {
        validateAppointment(appointmentId);

        return appointmentService.findOne(appointmentId);
    }

    private void validateAppointment(String appointmentId){
        boolean exists = appointmentService.exists(appointmentId);

        if(!exists){
            throw new AppointmentNotFoundException(appointmentId);
        }
    }

    @RequestMapping(value = "/Schedule", method = RequestMethod.GET)
    @ResponseBody
    List<Appointment> getScheduledAppointments() {
        List<Appointment> all = appointmentService.findAll();
        List<Appointment> scheduled = new ArrayList<>();
        for(Appointment appointment: all){
            if(appointment.getUser() != null){
                scheduled.add(appointment);
            }
        }

        return scheduled;
    }
}
