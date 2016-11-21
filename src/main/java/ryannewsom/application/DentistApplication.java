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
import ryannewsom.model.users.User;
import ryannewsom.model.users.entityinfo.ContactInfo;
import ryannewsom.model.users.entityinfo.Office;

import java.util.Calendar;
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
        //find appointments where patient == null
    }

    public static void main(String[] args) {
        SpringApplication.run(DentistApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        seedAppointments();
        appointmentService.deleteAll();
        appointmentService.save(new Appointment(new User("Ryan", "Newsom", new ContactInfo("3031753452", MockAddresses.OFFICE_1_ADDRESS)), new Date(), new Office(null)));

        System.out.println("Appointments found");
        System.out.println("------------------");

        for(Appointment a : appointmentService.findAll()){
            System.out.println(a);
        }
    }

    private void seedAppointments() {
        final long START_TIME = 1479654000000l;
        final double ONE_YEAR_NUM = 3.154;
        final double ONE_YEAR_EXPONENT = 10;

        final double ONE_HOUR_NUM = 3.6;
        final double ONE_HOUR_EXPONENT = 6;

        Calendar calendar = Calendar.getInstance();

        //12 months from today,
        //for every m-f each month
        //1 Hr Appointments available from 8-5
        long oneYearInMs = Math.round(Math.pow(ONE_HOUR_NUM, ONE_HOUR_EXPONENT));
        long endTime = START_TIME + oneYearInMs;
        long oneHourInMs = Math.round(Math.pow(ONE_HOUR_NUM, ONE_HOUR_EXPONENT));

        for(long i = START_TIME; i < endTime; i += oneHourInMs){
            Date current = new Date(i);
            calendar.setTime(current);
            int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            //2-7 is Monday-Friday
            if(dayOfWeek >= 2 && dayOfWeek <=6){
                if(hourOfDay >= 8 && hourOfDay <= 17){
                    Appointment appointment = new Appointment(null, current, new Office(new ContactInfo("3034541287", MockAddresses.OFFICE_1_ADDRESS)));
                    appointmentService.save(appointment);
                }
            }
        }
    }
}
