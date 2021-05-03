package com.cgi.dentistapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO which is used to put user to dentist visit by visit id.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDentistVisitDTO {

    @NotNull
    private Long id;
    @NotNull
    @Size(min = 1, max = 50)
    private String patientName;

    public UpdateDentistVisitDTO(Long id) {
        this.id = id;
    }
}
