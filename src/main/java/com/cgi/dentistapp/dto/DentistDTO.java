package com.cgi.dentistapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * DTO which is used to register new dentist, also is used to send information about dentist to front-end.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DentistDTO {

    @Null
    Long id;
    @NotNull
    @Size(min = 1, max = 50)
    String firstname;
    @NotNull
    @Size(min = 1, max = 50)
    String lastname;
}
