package ryannewsom.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ryannewsom.errors.AppointmentAlreadyScheduledException;
import ryannewsom.errors.AppointmentNotFoundException;
import ryannewsom.errors.MismatchIdsException;
import ryannewsom.model.appointment.Appointment;
import ryannewsom.model.users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A REST controller to handle Dentist Appointment CRUD
 */
@RestController
public class DentistAppointmentRestController  {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Server Online";
    }

    @RequestMapping(value = "/Appointment", method = RequestMethod.GET)
    @ResponseBody
    List<Appointment> getAvailableAppointments() {
        List<Appointment> appointments =  appointmentRepository.findByUser(null);
        List<Appointment> available = new ArrayList<>();

        for(Appointment appointment: appointments){
            if(!isOldAppointment(appointment)){
                available.add(appointment);
            } else{
                appointmentRepository.delete(appointment);
            }
        }

        return available;
    }

    @RequestMapping(value = "/Appointment/{appointmentId}", method = RequestMethod.POST)
    @ResponseBody
    void createAppointment(@PathVariable String appointmentId, @RequestBody Appointment appointment) {
        validateAppointment(appointmentId);

        if(appointmentId.equals(appointment.getAppointmentId())) {
             //appointmentRepository.findOne(appointmentId);
            Appointment unscheduledAppointment = appointmentRepository.findOneAndDelete(appointmentId);

            if (unscheduledAppointment.getUser() == null) {
                User patient = appointment.getUser();
                patient.setUserId();
                unscheduledAppointment.setUser(patient);
                appointmentRepository.save(unscheduledAppointment);
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

        return appointmentRepository.findOne(appointmentId);
    }

    private void validateAppointment(String appointmentId){
        boolean exists = appointmentRepository.exists(appointmentId);

        if(!exists){
            throw new AppointmentNotFoundException(appointmentId);
        }
    }

    @RequestMapping(value = "/Schedule", method = RequestMethod.GET)
    @ResponseBody
    List<Appointment> getScheduledAppointments() {
        List<Appointment> all = appointmentRepository.findAll();
        List<Appointment> scheduled = new ArrayList<>();
        for(Appointment appointment: all){
            if(appointment.getUser() != null){
                scheduled.add(appointment);
            }
        }

        Collections.sort(scheduled);

        return scheduled;
    }

    private boolean isOldAppointment(Appointment appointment){
        long currentTime = System.currentTimeMillis();
        long appointmentTime = appointment.getTime().getTime();

        return currentTime > appointmentTime;

    }
}
