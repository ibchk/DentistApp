package com.cgi.dentistapp.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * DTO which is used to add new free visit, which can be used by user to register himself/herself to it.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDentistVisitDTO {

    @NotNull
    Long dentistId;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date date;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    Date startTime;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    Date endTime;

    /**
     * @return true if endTime is after startTime
     */
    public boolean endTimeAfterStartTime() {
        return endTime.toInstant().isAfter(startTime.toInstant());
    }
}
