package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistDTO;
import com.cgi.dentistapp.dto.RegisterDentistVisitDTO;
import com.cgi.dentistapp.dto.UpdateDentistVisitDTO;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

/**
 * Controller for DentistVisitEntity.
 */
@Controller
@EnableAutoConfiguration
public class DentistVisitsController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    /**
     * Get controller for all.
     * <p>
     * Return:
     * If method is table: table page
     * If method is settings: visitAdding page
     * Else returns form page
     *
     * @param model  holder for model attributes.
     * @param method sign for controller to understand what to do
     * @return html pages
     */
    @GetMapping("/")
    public String showFormOrTable(Model model, @RequestParam(required = false, name = "m", defaultValue = "form") String method) {
        model.addAttribute("updateDentistVisitDTO", new UpdateDentistVisitDTO());
        if (method.equals("table")) {
            model.addAttribute("visits", dentistVisitService.getAllVisits(true));
            return "table";
        } else if (method.equals("settings")) {
            System.out.println("main: getting gettings");
            model.addAttribute("dentistList", dentistVisitService.getAllDentists());
            model.addAttribute("dentistDTO", new DentistDTO());
            model.addAttribute("registerVisitDTO", new RegisterDentistVisitDTO());
            return "visitAdding";
        }
        model.addAttribute("dentistList", dentistVisitService.getAllDentists());
        model.addAttribute("visitsWithoutPatient", dentistVisitService.getAllVisits(false));
        return "form";
    }

    /**
     * Post controller for all.
     *
     * Return:
     * If method is visit and valid or not registerDentistVisitDTO: visitAdding page
     * If method is update and valid updateDentistVisitDTO: redirect on results page
     * If method is delete: table page
     * If method is change and valid updateDentistVisitDTO: table page
     * Else returns form page
     *
     * @param model                    holder for model attributes.
     * @param updateDentistVisitDTO    DTO for dentist visit updating
     * @param bindingResultUpdateDTO   validation check for updateDentistVisitDTO
     * @param registerDentistVisitDTO  DTO for dentist visit adding
     * @param bindingResultRegisterDTO validation check for registerDentistVisitDTO
     * @param id                       visit id to delete
     * @param method                   sign for controller to understand what to do
     * @return html pages
     */
    @PostMapping("/")
    public String changeOrRegisterForm(Model model, @Valid UpdateDentistVisitDTO updateDentistVisitDTO, BindingResult bindingResultUpdateDTO, @Valid RegisterDentistVisitDTO registerDentistVisitDTO, BindingResult bindingResultRegisterDTO,
                                       @RequestParam(required = false, name = "id") Long id,
                                       @RequestParam(required = false, name = "m", defaultValue = "delete") String method) {
        if (method.equals("visit") && !bindingResultRegisterDTO.hasErrors()) {
            model.addAttribute("dentistDTO", new DentistDTO());
            model.addAttribute("registerVisitDTO", new RegisterDentistVisitDTO());
            dentistVisitService.addVisit(registerDentistVisitDTO.getDentistId(), registerDentistVisitDTO.getDate(), registerDentistVisitDTO.getStartTime(), registerDentistVisitDTO.getEndTime());
            return "visitAdding";

        } else if (method.equals("visit") && bindingResultRegisterDTO.hasErrors()) {
            model.addAttribute("dentistList", dentistVisitService.getAllDentists());
            model.addAttribute("dentistDTO", new DentistDTO());
            model.addAttribute("registerVisitDTO", new RegisterDentistVisitDTO());
            model.addAttribute("dentistList", dentistVisitService.getAllDentists());
            model.addAttribute("visitsWithoutPatient", dentistVisitService.getAllVisits(false));
            model.addAttribute("visits", dentistVisitService.getAllVisits(true));
            return "visitAdding";


        } else if (method.equals("update") && !bindingResultUpdateDTO.hasErrors()) {
            model.addAttribute("visits", dentistVisitService.change(updateDentistVisitDTO.getId(), updateDentistVisitDTO.getPatientName()));
            return "redirect:/results";

        } else if (id != null && method.equals("delete")) {
            model.addAttribute("visits", dentistVisitService.deleteVisit(id));
            return "table";

        } else if (method.equals("change") && !bindingResultUpdateDTO.hasErrors()) {
            model.addAttribute("visits", dentistVisitService.change(updateDentistVisitDTO.getId(), updateDentistVisitDTO.getPatientName()));
            return "table";

        } else if (method.equals("change") && bindingResultUpdateDTO.hasErrors()) {
            model.addAttribute("visits", dentistVisitService.getAllVisits(true));
            return "table";
        }
        model.addAttribute("dentistList", dentistVisitService.getAllDentists());
        model.addAttribute("visitsWithoutPatient", dentistVisitService.getAllVisits(false));
        return "form";
    }

    /**
     * Deletes visits, which have patients.
     *
     * @param model holder for model attributes.
     * @return table page
     */
    @DeleteMapping("/")
    public String clearTable(Model model) {
        model.addAttribute("visits", dentistVisitService.clearTable());
        return "table";
    }

}
