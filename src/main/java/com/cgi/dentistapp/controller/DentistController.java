package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistDTO;
import com.cgi.dentistapp.dto.RegisterDentistVisitDTO;
import com.cgi.dentistapp.service.DentistService;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@RequestMapping("dentist")
@Controller
@EnableAutoConfiguration
public class DentistController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistService dentistService;

    @Autowired
    private DentistVisitService dentistVisitService;

    @GetMapping("/")
    public String getAll(Model model) {
        System.out.println("------------------------");
        System.out.println("dentist: get all");
        model.addAttribute("dentistList", dentistService.getAll());
        return "dentists";
    }

    @PostMapping("/")
    public String registerDentist(Model model, @Valid DentistDTO dentistDTO, BindingResult bindingResult) {
        System.out.println("------------------------");
        if (bindingResult.hasErrors()) {
            System.out.println("dentist: Wrong dentist DTO");
            model.addAttribute("dentistList", dentistVisitService.getAllDentists());
            model.addAttribute("registerVisitDTO", new RegisterDentistVisitDTO());
            return "visitAdding";
        }
        System.out.println("dentist: post method");
        System.out.println("new dentist adding");
        dentistService.addDentist(dentistDTO.getFirstname(), dentistDTO.getLastname());
        return "redirect:/results";
    }
}
