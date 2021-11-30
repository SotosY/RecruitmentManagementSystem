package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantResumeCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantResumeReadService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationCreateService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationReadService;
import com.example.recruitmentsystemproject.Business.JobServices.JobCreateService;
import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.*;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins={"http://localhost:3000"}, allowedHeaders = "*", allowCredentials = "true")
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
    private ApplicationReadService applicationReadService;

    @Autowired
    private ApplicationCreateService applicationCreateService;

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

        Optional<User> user = userReadService.findByEmail("sotirisy@hotmail.com");

        System.out.println(theUser);
        System.out.println((principal));

//
//
//        if (principal instanceof UserDetailsImpl) {
//            Optional<User> user = userReadService.findByEmail(((UserDetailsImpl) principal).getUsername());
//
//            if (user.isPresent()) {
//                return ResponseEntity.ok(user);
//            }
//        }

//        Optional<User> user = userReadService.findByEmail("sotirisy@hotmail.com");
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
    public Job getJob(@PathVariable Long id) {
        Job job = jobReadService.findById(id).get();
        return job;
    }

    @GetMapping("/applicant/application/job/{id}/application")
    public Application getApplication (@PathVariable Long id) {

        User user = userReadService.findByEmail("sotirisy@hotmail.com").get();
        Applicant applicant = applicantReadService.findByUser(user).get();
        Job job = jobReadService.findById(id).get();
        ApplicantResume applicantResume = applicantResumeReadService.findByApplicant(applicant).get();

        Optional<Application> existingApplication = applicationReadService.findByJobAndApplicant(job, applicant);

        if (existingApplication.isPresent()) {
            return existingApplication.get();
        }

        Application newApplication = new Application();
        newApplication.setApplicant(applicant);
        newApplication.setApplicantResume(applicantResume);
        newApplication.setJob(job);
        applicationCreateService.saveApplication(newApplication);

        return newApplication;
    }

    @PostMapping("/applicant/application/job/apply")
    public String applyApplication(@RequestBody ObjectNode data) {

        User user = userReadService.findByEmail("sotirisy@hotmail.com").get();
        Applicant applicant = applicantReadService.findByUser(user).get();
        ApplicantResume applicantResume = applicantResumeReadService.findByApplicant(applicant).get();

        Long applicationId = data.get("applicationId").asLong();
        String dateOfBirth = data.get("dateOfBirth").asText();
        String gender = data.get("gender").asText();
        String phoneNumber = data.get("phoneNumber").asText();

        String education = data.get("education").asText();
        String experience = data.get("experience").asText();
        File cv = new File(data.get("cv").asText());
        File coverLetter = new File(data.get("coverLetter").asText());

        Application application = applicationReadService.findById(applicationId).get();

        applicant.setDateOfBirth(dateOfBirth);
        applicant.setGender(gender);
        applicant.setPhoneNumber(phoneNumber);
        applicantCreateService.saveApplicant(applicant);

        applicantResume.setEducation(education);
        applicantResume.setExperience(experience);
        applicantResume.setCv(cv.toString());
        applicantResume.setCoverLetter(coverLetter.toString());
        applicantResumeCreateService.saveApplicantResume(applicantResume);

        application.setApplyDate(LocalDate.now().toString());
        application.setApplicationStatus("Entered");
        applicationCreateService.saveApplication(application);


//            try {
//                SecurityContextHolder.getContext().setAuthentication(null);
//                request.login(user.getEmail(), user.getPassword());
//            } catch (ServletException e) {
//                System.out.println(e);
//                throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
//            }

        return "redirect:/careers/applicant/application";

    }

    @GetMapping("/applicant/application-history")
    public List<Application> applicantApplicationHistory() {

        User user = userReadService.findByEmail("sotirisy@hotmail.com").get();
        Applicant applicant = applicantReadService.findByUser(user).get();
        List<Application> application = applicationReadService.findByApplicant(applicant);
        return application;
    }

}
