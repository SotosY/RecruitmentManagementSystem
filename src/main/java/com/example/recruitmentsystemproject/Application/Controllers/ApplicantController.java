package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantResumeCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantResumeReadService;
import com.example.recruitmentsystemproject.Business.JobServices.JobCreateService;
import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Persistence.ApplicantRepositories.ApplicantResumeRepo;
import com.example.recruitmentsystemproject.Persistence.ApplicantRepositories.ApplicantResumeRepoJPA;
import com.example.recruitmentsystemproject.Security.UserDetailsImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins="*")
public class ApplicantController {

    @Autowired
    private ApplicantReadService applicantReadService;

    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private ApplicantResumeReadService applicantResumeReadService;

    @Autowired
    private ApplicantResumeCreateService applicantResumeCreateService;

    @Autowired
    private JobReadService jobReadService;

    @Autowired
    private JobCreateService jobCreateService;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserCreateService userCreateService;

    @PostMapping("/register/a")
    public String registerApplicant(@RequestBody ObjectNode data, HttpServletRequest request, BindingResult bindingResult) {

        String pass = data.get("password").asText();
        String email = data.get("email").asText();
        String firstName = data.get("firstName").asText();
        String lastName = data.get("lastName").asText();

        if (bindingResult.hasErrors()){
            System.out.println("Errors" + bindingResult.getFieldError());
            for (ObjectError oe : bindingResult.getAllErrors()) {
                System.out.println(oe);
            }

            return "redirect:/careers/register/a";

        } else {

            User user = new User();
            Applicant applicant = new Applicant();
            ApplicantResume applicantResume = new ApplicantResume();

            user.setEmail(email);
            user.setPassword(pass);
            user.setRoles("APPLICANT");
            userCreateService.saveUser(user);

            applicant.setUser(user);
            applicant.setFirstName(firstName);
            applicant.setLastName(lastName);
            applicantCreateService.saveApplicant(applicant);

            applicantResume.setApplicant(applicant);
            applicantResumeCreateService.saveApplicantResume(applicantResume);

//            try {
//                SecurityContextHolder.getContext().setAuthentication(null);
//                request.login(user.getEmail(), user.getPassword());
//            } catch (ServletException e) {
//                System.out.println(e);
//                throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
//            }

            return "redirect:/careers/applicant/dashboard";

        }
    }

    @GetMapping("/applicant/dashboard")
    public ResponseEntity<?> applicantDashboard() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        Optional<User> theUser = userReadService.findByEmail(((UserDetailsImpl)principal).getUsername());
//
//
//        if (principal instanceof UserDetailsImpl) {
//            Optional<User> user = userReadService.findByEmail(((UserDetailsImpl) principal).getUsername());
//
//            if (user.isPresent()) {
//                return ResponseEntity.ok(user);
//            }
//        }

        Optional<User> user = userReadService.findByEmail("sotirisy@hotmail.com");
        Optional<Applicant> applicant = applicantReadService.findByUser(theUser.get());


       return ResponseEntity.ok(applicant);
    }

    @GetMapping("/applicant/profile")
    public ApplicantResume applicantProfile() {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Optional<User> user = userReadService.findByEmail(((UserDetailsImpl)principal).getUsername());
//        Optional<Employer> employer = employerReadService.findByUser(user.get());

        Optional<User> user = userReadService.findByEmail("sotirisy@hotmail.com");
        Applicant applicant = applicantReadService.findByUser(user.get()).get();
        ApplicantResume applicantResume = applicantResumeReadService.findByApplicant(applicant).get();

//        ApplicantResume applicantResume = new ApplicantResume();
//        applicantResume.setApplicant(applicant.get());
//        applicantCreateService.saveApplicantResume(applicantResume);

        return applicantResume;
    }

    @PostMapping("/applicant/profile/save")
    public String saveApplicantProfileDetails(@RequestBody ObjectNode data, BindingResult bindingResult) {

        String address = data.get("address").asText();
        String city = data.get("city").asText();
        String country = data.get("country").asText();
        String postcode = data.get("postcode").asText();
        String dateOfBirth = data.get("dateOfBirth").asText();
        String gender = data.get("gender").asText();
        String phoneNumber = data.get("phoneNumber").asText();

        String education = data.get("education").asText();
        String experience = data.get("experience").asText();


            User user = userReadService.findByEmail("sotirisy@hotmail.com").get();
            Applicant applicant = applicantReadService.findByUser(user).get();
            ApplicantResume applicantResume = applicantResumeReadService.findByApplicant(applicant).get();

            applicant.setAddress(address);
            applicant.setCity(city);
            applicant.setCountry(country);
            applicant.setPostcode(postcode);
            applicant.setDateOfBirth(dateOfBirth);
            applicant.setGender(gender);
            applicant.setPhoneNumber(phoneNumber);
            applicantCreateService.saveApplicant(applicant);

            applicantResume.setApplicant(applicant);
            applicantResume.setEducation(education);
            applicantResume.setExperience(experience);
            applicantResumeCreateService.saveApplicantResume(applicantResume);


//            try {
//                SecurityContextHolder.getContext().setAuthentication(null);
//                request.login(user.getEmail(), user.getPassword());
//            } catch (ServletException e) {
//                System.out.println(e);
//                throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
//            }

            return "redirect:/careers/applicant/profile";

    }

    @GetMapping("/applicant/application/job/{id}")
    public Job applicantApplication(@PathVariable Long id) {
        Job job = jobReadService.findById(id).get();
        return job;
    }

    @GetMapping("/applicant/application-history")
    public String applicantAppicationHistory() {
        return "applicant-application-history";
    }
}
