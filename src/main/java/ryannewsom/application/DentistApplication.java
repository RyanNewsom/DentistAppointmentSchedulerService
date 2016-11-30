package ryannewsom.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ryannewsom.model.appointment.Appointment;
import ryannewsom.model.mock.MockOffices;
import ryannewsom.model.users.entityinfo.ContactInfo;
import ryannewsom.model.users.entityinfo.Office;

import java.util.*;

/**
 * A Dentist's office Backend, It's main service, AppointmentRepository, is injected via Dependency Injection......
 * by :Ryan Newsom
 */
@SpringBootApplication
public class DentistApplication implements CommandLineRunner {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DentistApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //clean the db
        appointmentRepository.deleteAll();
        seedAppointments();
        System.out.println("Appointments found");
        System.out.println("------------------");

        for(Appointment a : appointmentRepository.findAll()){
            System.out.println(a);
        }
    }

    //Seeds Appointments for 1 year from 11/24/2016
    private void seedAppointments() {
        final Office office = new Office(new ContactInfo("3034541287", MockOffices.OFFICE_1_ADDRESS));
        long ONE_HOUR_MS = 3600000;
        final long START_TIME = 1479999600000l;
        long ONE_YEAR_MS;
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
                    appointmentRepository.save(appointment);
                }
            }
        }
    }
}
