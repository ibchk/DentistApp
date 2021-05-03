package com.cgi.dentistapp;

import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repository.DentistRepository;
import com.cgi.dentistapp.repository.DentistVisitRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Adding data in db when running application
 */
@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(DentistVisitRepository dentistVisitRepository, DentistRepository dentistRepository) throws ParseException {
        //format of time
        SimpleDateFormat dateformat;

        // time of the visits to visits with patients
        dateformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date startDate1 = dateformat.parse("02-04-2021 11:30:00");
        Date endDate1 = dateformat.parse("02-04-2021 11:55:00");
        Date startDate2 = dateformat.parse("12-05-2021 16:10:00");
        Date endDate2 = dateformat.parse("12-05-2021 16:45:00");
        Date startDate3 = dateformat.parse("23-04-2023 9:30:00");
        Date endDate3 = dateformat.parse("23-04-2023 10:10:00");
        Date startDate4 = dateformat.parse("13-07-2022 14:20:00");
        Date endDate4 = dateformat.parse("13-07-2022 14:50:00");
        Date startDate5 = dateformat.parse("12-11-2022 14:20:00");
        Date endDate5 = dateformat.parse("12-11-2022 15:30:00");

        // time of the visits to visits without patients
        Date startDate6 = dateformat.parse("01-04-2021 11:30:00");
        Date endDate6 = dateformat.parse("01-04-2021 11:45:00");
        Date startDate7 = dateformat.parse("22-05-2021 13:45:00");
        Date endDate7 = dateformat.parse("22-05-2021 14:00:00");
        Date startDate8 = dateformat.parse("23-04-2023 14:30:00");
        Date endDate8 = dateformat.parse("23-04-2023 15:15:00");
        Date startDate9 = dateformat.parse("12-07-2022 13:45:00");
        Date endDate9 = dateformat.parse("12-07-2022 14:45:00");
        Date startDate10 = dateformat.parse("12-12-2022 16:45:00");
        Date endDate10 = dateformat.parse("12-12-2022 17:10:00");

        //dentists
        DentistEntity den1 = new DentistEntity("Karl", "Mägi");
        DentistEntity den2 = new DentistEntity("Allan", "Vool");
        DentistEntity den3 = new DentistEntity("Janar", "Saks");
        DentistEntity den4 = new DentistEntity("Kertu", "Ojamäe");
        DentistEntity den5 = new DentistEntity("Anna", "Pihlakas");
        return args -> {
            //Adding visits with patients in database
            List<DentistEntity> dentists = Arrays.asList(den1, den2, den3, den4, den5);
            dentistRepository.save(dentists);
            List<DentistVisitEntity> completeVisits = Arrays.asList(
                    new DentistVisitEntity("Mike Kottise", den1, startDate1, endDate1),
                    new DentistVisitEntity("Kevin Visnapuu", den2, startDate2, endDate2),
                    new DentistVisitEntity("Laura Rüütel", den3, startDate3, endDate3),
                    new DentistVisitEntity("Alex Smith", den4, startDate4, endDate4),
                    new DentistVisitEntity("Robin Uustalu", den5, startDate5, endDate5));
            dentistVisitRepository.save(completeVisits);

            //Adding visits without patients in database
            List<DentistVisitEntity> visitsWithout = Arrays.asList(
                    new DentistVisitEntity(den1, startDate6, endDate6),
                    new DentistVisitEntity(den2, startDate7, endDate7),
                    new DentistVisitEntity(den2, startDate8, endDate8),
                    new DentistVisitEntity(den1, startDate9, endDate9),
                    new DentistVisitEntity(den4, startDate10, endDate10)
            );
            dentistVisitRepository.save(visitsWithout);
        };
    }
}
