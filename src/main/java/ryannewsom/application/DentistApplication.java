package ryannewsom.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ryannewsom.model.appointment.Appointment;
import ryannewsom.model.repos.MockAddresses;
import ryannewsom.model.users.Patient;
import ryannewsom.model.users.entityinfo.ContactInfo;
import ryannewsom.model.users.entityinfo.Office;
import ryannewsom.model.users.entityinfo.PhysicalAddress;

import java.util.Date;
import java.util.List;

@Controller
@SpringBootApplication
public class DentistApplication implements CommandLineRunner {
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello Ryan!";
    }

    @RequestMapping(value = "/Appointment", method = RequestMethod.GET)
    @ResponseBody
    List<Appointment> getAvailableAppointments() {
        return appointmentService.findAll();
    }

    @RequestMapping(value = "/Appointment", method = RequestMethod.POST)
    @ResponseBody
    void createAppointment(@RequestBody Appointment appointment) {
        appointmentService.save(appointment);
    }

    @RequestMapping(value = "/Schedule", method = RequestMethod.GET)
    @ResponseBody
    List<Appointment> getScheduledAppointments() {
        return appointmentService.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(DentistApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        appointmentService.deleteAll();
        appointmentService.save(new Appointment(new Patient("Ryan", "Newsom", new ContactInfo("3031753452", MockAddresses.OFFICE_1_ADDRESS)), new Date(1), new Office()));

        System.out.println("Appointments found");
        System.out.println("------------------");

        for(Appointment a : appointmentService.findAll()){
            System.out.println(a);
        }
    }
}
