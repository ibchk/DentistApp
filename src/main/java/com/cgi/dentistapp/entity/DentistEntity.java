package com.cgi.dentistapp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Entity to keep data about dentist in database.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dentist", schema = "clinic")
public class DentistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 1, max = 50)
    String firstname;
    @Size(min = 1, max = 50)
    String lastname;

    public DentistEntity(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
