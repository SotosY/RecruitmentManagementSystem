package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins="*")
public class EmployerController {

    @Autowired
    private EmployerReadService employerReadService;

    @Autowired
    private EmployerCreateService employerCreateService;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserCreateService userCreateService;

    @PostMapping("/register/e")
    public String registerEmployer(@RequestBody User user, HttpServletRequest request, BindingResult bindingResult, Employer employer) {

        if (bindingResult.hasErrors()){
            System.out.println("Errors" + bindingResult.getFieldError());
            for (ObjectError oe : bindingResult.getAllErrors()) {
                System.out.println(oe);
            }

            return "redirect:/careers/register/e";

        } else {

            user.setRoles("EMPLOYER");
            userCreateService.saveUser(user);
            employer.setUser(user);
            employerCreateService.saveEmployer(employer);
            User theUser = userReadService.findById(user.getUserId()).get();

            try {
                SecurityContextHolder.getContext().setAuthentication(null);
                request.login(theUser.getEmail(), theUser.getPassword());
            } catch (ServletException e) {
                System.out.println(e);
                throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
            }

            return "redirect:/careers/employer/dashboard";

        }
    }

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
