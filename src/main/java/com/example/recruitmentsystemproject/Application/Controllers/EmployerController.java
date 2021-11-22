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
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public String registerEmployer(@RequestBody ObjectNode data, HttpServletRequest request, BindingResult bindingResult) {

        String pass = data.get("password").asText();
        String email = data.get("email").asText();
        String firstName = data.get("firstName").asText();
        String lastName = data.get("lastName").asText();
        String company = data.get("company").asText();

        System.out.println(company);

        if (bindingResult.hasErrors()){
            System.out.println("Errors" + bindingResult.getFieldError());
            for (ObjectError oe : bindingResult.getAllErrors()) {
                System.out.println(oe);
            }

            return "redirect:/careers/register/e";

        } else {

            User user = new User();
            Employer employer = new Employer();

            user.setEmail(email);
            user.setPassword(pass);
            user.setRoles("EMPLOYER");
            userCreateService.saveUser(user);

            employer.setUser(user);
            employer.setFirstName(firstName);
            employer.setLastName(lastName);
            employer.setCompany(company);
            employerCreateService.saveEmployer(employer);


//            try {
//                SecurityContextHolder.getContext().setAuthentication(null);
//                request.login(user.getEmail(), user.getPassword());
//            } catch (ServletException e) {
//                System.out.println(e);
//                throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
//            }

            return "redirect:/careers/employer/dashboard";

        }
    }

    @GetMapping("/employer/dashboard")
    public ResponseEntity<?> employerDashboard() {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal);
//
//        Optional<User> user = userReadService.findByEmail(((UserDetailsImpl)principal).getUsername());

        Optional<User> user = userReadService.findByEmail("bob123@hotmail.com");
        Optional<Employer> employer = employerReadService.findByUser(user.get());


        return ResponseEntity.ok(employer);
    }

    @GetMapping("/employer/profile")
    public Optional<Employer> employerProfile() {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Optional<User> user = userReadService.findByEmail(((UserDetailsImpl)principal).getUsername());
//        Optional<Employer> employer = employerReadService.findByUser(user.get());

        Optional<User> user = userReadService.findByEmail("bob123@hotmail.com");
        Optional<Employer> employer = employerReadService.findByUser(user.get());

        return employer;
    }

    @PostMapping("/employer/profile/save")
    public String saveEmployerProfileDetails(@RequestBody ObjectNode data, HttpServletRequest request, BindingResult bindingResult) {

        String address = data.get("address").asText();
        String city = data.get("city").asText();
        String country = data.get("country").asText();
        String postcode = data.get("postcode").asText();
        String contactName = data.get("contactName").asText();
        String businessType = data.get("businessType").asText();
        String companyEmail = data.get("companyEmail").asText();
        String telephoneNumber = data.get("telephoneNumber").asText();
        String companyProfile = data.get("companyProfile").asText();


        if (bindingResult.hasErrors()){
            System.out.println("Errors" + bindingResult.getFieldError());
            for (ObjectError oe : bindingResult.getAllErrors()) {
                System.out.println(oe);
            }

            return "redirect:/careers/employer/profile";

        } else {

            User user = userReadService.findByEmail("bob123@hotmail.com").get();
            Employer employer = employerReadService.findByUser(user).get();

            employer.setAddress(address);
            employer.setCity(city);
            employer.setCountry(country);
            employer.setPostcode(postcode);
            employer.setContactName(contactName);
            employer.setBusinessType(businessType);
            employer.setCompanyEmail(companyEmail);
            employer.setTelephoneNumber(telephoneNumber);
            employer.setCompanyProfile(companyProfile);
            employerCreateService.saveEmployer(employer);


//            try {
//                SecurityContextHolder.getContext().setAuthentication(null);
//                request.login(user.getEmail(), user.getPassword());
//            } catch (ServletException e) {
//                System.out.println(e);
//                throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
//            }

            return "redirect:/careers/employer/profile";

        }
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
