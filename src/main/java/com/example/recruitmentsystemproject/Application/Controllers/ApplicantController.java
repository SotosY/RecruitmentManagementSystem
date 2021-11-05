package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/careers")
public class ApplicantController {

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private UserReadService userReadService;

    @GetMapping("/applicant/dashboard")
    public String applicantDashboard() {

        boolean applicantIsEmpty = true;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Optional<User> user = userReadService.findByEmail(((UserDetailsImpl)principal).getUsername());

            if (user.isPresent()) {
                Optional<Applicant> applicant = applicantReadService.findByUser(user.get());

                if (applicant.isPresent()) {
                    applicantIsEmpty = false;
                }
            }
        } if (!applicantIsEmpty) {
            return "applicant-dashboard";
        } else {
            return "applicant/profile";
        }
    }


    @GetMapping("/applicant/profile")
    public String applicantProfile() {
        return "applicant-profile";
    }

    @GetMapping("/applicant/application")
    public String applicantApplication() {
        return "applicant-application";
    }

    @GetMapping("/applicant/application-history")
    public String applicantAppicationHistory() {
        return "applicant-application-history";
    }
}
