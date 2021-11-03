package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/careers")
public class ApplicantController {

    @Autowired
    private ApplicantReadService applicantReadService;

    @GetMapping("/applicant/dashboard")
    public String applicantDashboard() {
        return "applicant-dashboard";
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
