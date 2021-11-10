package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins="*")
public class ApplicantController {

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserCreateService userCreateService;

    @PostMapping("/register/a")
    public String registerApplicant(@RequestBody User user, HttpServletRequest request, BindingResult bindingResult, Applicant applicant) {

        if (bindingResult.hasErrors()){
            System.out.println("Errors" + bindingResult.getFieldError());
            for (ObjectError oe : bindingResult.getAllErrors()) {
                System.out.println(oe);
            }

            return "redirect:/careers/register/a";

        } else {

            user.setRoles("APPLICANT");
            userCreateService.saveUser(user);
            applicant.setUser(user);
            applicantCreateService.saveApplicant(applicant);
//            User theUser = userReadService.findById(user.getUserId()).get();

            try {
                SecurityContextHolder.getContext().setAuthentication(null);
                request.login(user.getEmail(), user.getPassword());
            } catch (ServletException e) {
                System.out.println(e);
                throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
            }

            return "redirect:/careers/applicant/dashboard";

        }
    }

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


    @GetMapping("/applicant/profile/{id}")
    public String applicantProfile(@PathVariable Long id, Model model) {
        ApplicantResume applicantResume = new ApplicantResume();
        Optional<Applicant> applicant = applicantReadService.findById(id);
        applicantResume.setApplicant(applicant.get());
        model.addAttribute("ApplicantProfile", applicantResume);
        return "applicant/profile";
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
