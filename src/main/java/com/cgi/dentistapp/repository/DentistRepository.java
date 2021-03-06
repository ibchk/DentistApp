package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dentist repository.
 */
@Repository
public interface DentistRepository extends JpaRepository<DentistEntity, Long> {
}
