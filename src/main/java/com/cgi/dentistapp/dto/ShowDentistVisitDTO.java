package com.cgi.dentistapp.dto;

import lombok.*;

import java.util.Calendar;
import java.util.Date;

/**
 * DTO which is used to send information about dentist visit to front-end.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowDentistVisitDTO {

    Long id;
    Long orderNr;
    String patientName;
    Long dentistId;
    String dentistName;
    String time;

    public ShowDentistVisitDTO(Long id, Long orderNr, String patientName, Long dentistId, String dentistName, Date visitStart, Date visitEnd) {
        this.id = id;
        this.orderNr = orderNr;
        this.patientName = patientName;
        this.dentistId = dentistId;
        this.dentistName = dentistName;
        Calendar time = Calendar.getInstance();
        time.setTime(visitStart);
        String timeString = time.get(Calendar.YEAR) + "." + time.get(Calendar.MONTH) + "." + time.get(Calendar.DAY_OF_MONTH) + " " + time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE);
        time.setTime(visitEnd);
        timeString += "-" + time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE);
        this.time = timeString;
    }

    public ShowDentistVisitDTO(Long id, String patientName, Long dentistId, String dentistName, Date visitStart, Date visitEnd) {
        this.id = id;
        this.patientName = patientName;
        this.dentistId = dentistId;
        this.dentistName = dentistName;
        Calendar time = Calendar.getInstance();
        time.setTime(visitStart);
        String timeString = time.get(Calendar.YEAR) + "." + time.get(Calendar.MONTH) + "." + time.get(Calendar.DAY_OF_MONTH) + " " + time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE);
        time.setTime(visitEnd);
        timeString += "-" + time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE);
        this.time = timeString;
    }
}
