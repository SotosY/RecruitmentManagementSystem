package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantResumeCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantResumeReadService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationCreateService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationReadService;
import com.example.recruitmentsystemproject.Business.FileServices.FileStorageService;
import com.example.recruitmentsystemproject.Business.JobServices.JobCreateService;
import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.*;
import com.example.recruitmentsystemproject.Security.UserDetailsImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.recruitmentsystemproject.Application.Controllers.UserController.user;

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

    @Autowired
    private FileStorageService fileStorageService;


    // POST Request - Registers an applicant
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

            User theUser = new User();
            Applicant applicant = new Applicant();
            ApplicantResume applicantResume = new ApplicantResume();

            theUser.setEmail(email);
            theUser.setPassword(pass);
            theUser.setRoles("APPLICANT");
            userCreateService.saveUser(theUser);

            applicant.setUser(theUser);
            applicant.setFirstName(firstName);
            applicant.setLastName(lastName);
            applicantCreateService.saveApplicant(applicant);

            applicantResume.setApplicant(applicant);
            applicantResumeCreateService.saveApplicantResume(applicantResume);

            user = userReadService.findByEmail(email).get();

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

    // GET Request - Returns to Applicant's dashboard
    @GetMapping("/applicant/dashboard")
    public ResponseEntity<?> applicantDashboard() {


        Optional<User> theUser = userReadService.findByEmail(user.getEmail());

            if (theUser.isPresent()) {
                return ResponseEntity.ok(user);
            }

       return ResponseEntity.badRequest().body("No user found");
    }

    // GET Request - Returns to Applicant's profile
    @GetMapping("/applicant/profile")
    public ApplicantResume applicantProfile() {

        Applicant applicant = applicantReadService.findByUser(user).get();
        ApplicantResume applicantResume = applicantResumeReadService.findByApplicant(applicant).get();

        return applicantResume;
    }

    // POST Request - Saves Applicant's profile details
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

            return "redirect:/careers/applicant/profile";

    }

    // GET Request - Returns to job details
    @GetMapping("/applicant/application/job/{id}")
    public Job getJob(@PathVariable Long id) {
        Job job = jobReadService.findById(id).get();
        return job;
    }

    // GET Request - Returns to application form
    @GetMapping("/applicant/application/job/{id}/application")
    public Application getApplication (@PathVariable Long id) {

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

    // POST Request - Applies to the application
    @PostMapping("/applicant/application/job/apply")
    public String applyApplication(@RequestBody ObjectNode data) {

        Applicant applicant = applicantReadService.findByUser(user).get();
        ApplicantResume applicantResume = applicantResumeReadService.findByApplicant(applicant).get();

        System.out.println(data);
        Long applicationId = data.get("applicationId").asLong();
        String dateOfBirth = data.get("dateOfBirth").asText();
        String gender = data.get("gender").asText();
        String phoneNumber = data.get("phoneNumber").asText();

        String education = data.get("education").asText();
        String experience = data.get("experience").asText();
        String cv = data.get("cv").asText();
        String coverLetter = data.get("coverLetter").asText();

        //Converts to file name
        cv = cv.substring(cv.lastIndexOf("\\") + 1);
        coverLetter = coverLetter.substring(coverLetter.lastIndexOf("\\") + 1);


//        String fileName = fileStorageService.storeFile(cvFile);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentRequestUri()
//                .path("/files/")
//                .path(fileName)
//                .toUriString();
//
//        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri, cvFile.getContentType(), cvFile.getSize());

        Application application = applicationReadService.findById(applicationId).get();

        applicant.setDateOfBirth(dateOfBirth);
        applicant.setGender(gender);
        applicant.setPhoneNumber(phoneNumber);
        applicantCreateService.saveApplicant(applicant);

        applicantResume.setEducation(education);
        applicantResume.setExperience(experience);
        applicantResume.setCv(cv);
        applicantResume.setCoverLetter(coverLetter);
        applicantResumeCreateService.saveApplicantResume(applicantResume);

        application.setApplyDate(LocalDate.now().toString());
        application.setApplicationStatus("Entered");
        applicationCreateService.saveApplication(application);


        return "redirect:/careers/applicant/application";
    }

    // GET Request - Returns to Applicant's application history
    @GetMapping("/applicant/application-history")
    public List<Application> applicantApplicationHistory() {

        Applicant applicant = applicantReadService.findByUser(user).get();
        List<Application> application = applicationReadService.findByApplicant(applicant);
        return application;
    }

}
