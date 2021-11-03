package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerCreateService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/careers")
public class EmployerController {

    @Autowired
    private EmployerReadService employerReadService;

    @Autowired
    private EmployerCreateService employerCreateService;

    @GetMapping("/employer/dashboard")
    public String employerDashboard() {
        return "employer-dashboard";
    }

    @GetMapping("/employer/profile")
    public String employerProfile() {
        return "employer-profile";
    }

    @GetMapping("/employer/vacancies")
    public String employerVacancies() {
        return "employer-vacancies";
    }

    @GetMapping("/employer/vacancy-history")
    public String employerVacancyHistory() {
        return "employer-vacancy-history";
    }

}
