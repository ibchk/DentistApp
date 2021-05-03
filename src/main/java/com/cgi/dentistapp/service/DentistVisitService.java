package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistDTO;
import com.cgi.dentistapp.dto.ShowDentistVisitDTO;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repository.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Service for communication with DentistVisitEntity table in database.
 */
@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private DentistVisitRepository dentistVisitRepository;

    @Autowired
    private DentistService dentistService;

    /**
     * Method to add new visit to database. Visit would be without patient.
     * Uses visitDate, startTime and endTime and combines from it two Date parameters.
     *
     * @param dentistId id of dentist to add DentistEntity to visit
     * @param visitDate A date when the visit would be
     * @param startTime A time of visit start
     * @param endTime   A time of visit end
     */
    public void addVisit(long dentistId, Date visitDate, Date startTime, Date endTime) {
        DentistEntity dentist = dentistService.getById(dentistId);
        Calendar time = Calendar.getInstance();
        time.setTime(startTime);
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(visitDate);
        calStart.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        calStart.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(visitDate);
        time.setTime(endTime);
        calEnd.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        calEnd.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        Date start = calStart.getTime();
        System.out.println("start: " + start);
        Date end = calEnd.getTime();
        System.out.println("end: " + end);
        dentistVisitRepository.save(new DentistVisitEntity(dentist, start, end));
    }

    /**
     * Method to get all visits. If parameter is true, then returns all visits without
     * patients (free to add new patients), else returns all visits with patients.
     *
     * @param withPatients boolean to get visits with patients or not.
     * @return List of dentist visits as ShowDentistVisitDTO
     */
    public List<ShowDentistVisitDTO> getAllVisits(boolean withPatients) {
        List<ShowDentistVisitDTO> dentistVisitDTOS = new LinkedList<>();
        List<DentistVisitEntity> dentistVisitEntities = dentistVisitRepository.findAll();
        DentistVisitEntity entity;
        int order = 0;
        for (int i = 0; i < dentistVisitEntities.size(); i++) {
            entity = dentistVisitEntities.get(i);
            if (withPatients) {
                if (entity.getPatientName() != null) {
                    order++;
                    String dentistName = entity.getDentist().getFirstname() + " " + entity.getDentist().getLastname();
                    dentistVisitDTOS.add(new ShowDentistVisitDTO(entity.getId(), (long) (order), entity.getPatientName(),
                            entity.getDentist().getId(), dentistName, entity.getVisitStart(), entity.getVisitEnd()));
                }
            } else {
                if (entity.getPatientName() == null) {
                    String dentistName = entity.getDentist().getFirstname() + " " + entity.getDentist().getLastname();
                    dentistVisitDTOS.add(new ShowDentistVisitDTO(entity.getId(), entity.getPatientName(),
                            entity.getDentist().getId(), dentistName, entity.getVisitStart(), entity.getVisitEnd()));
                }
            }
        }
        return dentistVisitDTOS;
    }

    /**
     * Method to change the name of patient in visit.
     * Also method is used to add a name to visits, where there is no patient.
     *
     * @param id          DentistVisitEntity id is as long
     * @param patientName name of the patient to be added or changed for
     * @return List of dentist visits as ShowDentistVisitDTO
     */
    public List<ShowDentistVisitDTO> change(long id, String patientName) {
        DentistVisitEntity dentistVisitEntity = dentistVisitRepository.findOne(id);
        dentistVisitEntity.setPatientName(patientName);
        dentistVisitRepository.save(dentistVisitEntity);
        return getAllVisits(true);
    }

    /**
     * Method to delete visit by id if such visit exists.
     *
     * @param id DentistVisitEntity id as long
     * @return List of dentist visits as ShowDentistVisitDTO
     */
    public List<ShowDentistVisitDTO> deleteVisit(long id) {
        if (dentistVisitRepository.exists(id)) {
            dentistVisitRepository.delete(id);
        }
        return getAllVisits(true);
    }

    /**
     * Method to delete all DentistVisitEntities which have patient names.
     *
     * @return List of dentist visits as ShowDentistVisitDTO
     */
    public List<ShowDentistVisitDTO> clearTable() {
        for (DentistVisitEntity visit : dentistVisitRepository.findAll()) {
            if (visit.getPatientName() != null) {
                deleteVisit(visit.getId());
            }
        }
        return getAllVisits(true);
    }

    /**
     * Method to get all dentists as DentistDTO.
     *
     * @return List of dentists as DentistDTO
     */
    public List<DentistDTO> getAllDentists() {
        return dentistService.getAll();
    }

}
