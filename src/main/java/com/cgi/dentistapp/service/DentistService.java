package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistDTO;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.repository.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * Service for communication with DentistEntity table in database.
 */
@Service
@Transactional
public class DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    /**
     * Method to get all dentists.
     *
     * @return list of dentists as DentistDTO
     */
    public List<DentistDTO> getAll() {
        List<DentistDTO> dtos = new LinkedList<>();
        for (DentistEntity entity : dentistRepository.findAll()) {
            dtos.add(new DentistDTO(entity.getId(), entity.getFirstname(), entity.getLastname()));
        }
        return dtos;
    }

    /**
     * Method to add new dentist in database.
     *
     * @param firstname dentist firstname
     * @param lastname  dentist lastname
     */
    public void addDentist(String firstname, String lastname) {
        dentistRepository.save(new DentistEntity(firstname, lastname));
    }

    /**
     * Method to get dentist by id. It is used by DentistVisitService in addVisit()
     * method to add dentist to the visit which would be created.
     *
     * @param id saved dentis id in database
     * @return
     */
    public DentistEntity getById(long id) {
        return dentistRepository.findOne(id);
    }
}
