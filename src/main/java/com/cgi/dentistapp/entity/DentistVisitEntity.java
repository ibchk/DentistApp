package com.cgi.dentistapp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entity to keep data about dentist visit in database. When creating, then patient name is not needed.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dentist_visit", schema = "clinic")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String patientName;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "dentist", nullable = false)
    private DentistEntity dentist;
    @NotNull
    private Date visitStart;
    @NotNull
    private Date visitEnd;

    public DentistVisitEntity(DentistEntity dentist, Date visitStart, Date visitEnd) {
        this.dentist = dentist;
        this.visitStart = visitStart;
        this.visitEnd = visitEnd;
    }

    public DentistVisitEntity(String patientName, DentistEntity dentist, Date visitStart, Date visitEnd) {
        this.patientName = patientName;
        this.dentist = dentist;
        this.visitStart = visitStart;
        this.visitEnd = visitEnd;
    }
}
