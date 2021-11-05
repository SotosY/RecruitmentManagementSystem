package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerCreateService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/careers")
public class EmployerController {

    @Autowired
    private EmployerReadService employerReadService;

    @Autowired
    private EmployerCreateService employerCreateService;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserCreateService userCreateService;

    @GetMapping("/employer/dashboard")
    public String employerDashboard() {

        boolean employerIsEmpty = true;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> user = userReadService.findByEmail(((UserDetailsImpl)principal).getUsername());

            if (user.isPresent()) {
                Optional<Employer> employer = employerReadService.findByUser(user.get());

                if (employer.isPresent()) {
                    employerIsEmpty = false;
                }
            }
        } if (!employerIsEmpty) {
            return "employer-dashboard";
        } else {
            return "employer-profile";
        }
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
