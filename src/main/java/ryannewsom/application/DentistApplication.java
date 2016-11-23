package ryannewsom.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ryannewsom.model.appointment.Appointment;
import ryannewsom.model.repos.MockAddresses;
import ryannewsom.model.users.User;
import ryannewsom.model.users.entityinfo.ContactInfo;
import ryannewsom.model.users.entityinfo.Office;

import java.util.*;

@Controller
@SpringBootApplication
public class DentistApplication implements CommandLineRunner {
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
        if(appointmentId != null && appointmentId.equals(appointment.getAppointmentId())) {
            Appointment unscheduledAppointment = appointmentService.findOne(appointmentId);
            if (unscheduledAppointment == null) {
                return;
            }

            if (unscheduledAppointment.getUser() == null) {
                User patient = appointment.getUser();
                patient.setUserId();
                unscheduledAppointment.setUser(patient);
                appointmentService.save(unscheduledAppointment);
            }
        } else {
            return;
        }
    }

    @RequestMapping(value = "/Appointment/{appointmentId}", method = RequestMethod.GET)
    @ResponseBody
    Appointment getAppointmentById(@PathVariable String appointmentId) {
        return appointmentService.findOne(appointmentId);
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

    public static void main(String[] args) {
        SpringApplication.run(DentistApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        appointmentService.deleteAll();
        seedAppointments();
        System.out.println("Appointments found");
        System.out.println("------------------");

        for(Appointment a : appointmentService.findAll()){
            System.out.println(a);
        }
    }

    //Seeds Appointments for 1 year from Nov/17/2016
    private void seedAppointments() {
        //test comment
        final Office office = new Office(new ContactInfo("3034541287", MockAddresses.OFFICE_1_ADDRESS));
        final long START_TIME = 1479654000000l;
        long ONE_YEAR_MS;
        long ONE_HOUR_MS = 3600000;
        ONE_YEAR_MS = ONE_HOUR_MS * 24 * 365;
        long endTime = START_TIME + ONE_YEAR_MS;

        System.out.println("SeedAppointments()");

        for(long i = START_TIME; i < endTime; i += ONE_HOUR_MS){
            Calendar calendar = Calendar.getInstance();
            Date current = new Date(i);

            calendar.setTime(current);
            calendar.setTimeZone(TimeZone.getTimeZone("America/Denver"));
            int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            //2-6 is Monday-Friday
            if(dayOfWeek >= 2 && dayOfWeek <=6){
                if(hourOfDay >= 8 && hourOfDay <= 17){
                    System.out.println("Seeding...");
                    Appointment appointment = new Appointment(null, current, office);
                    appointmentService.save(appointment);
                }
            }
        }
    }
}
