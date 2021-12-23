package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationCreateService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationReadService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerCreateService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerReadService;
import com.example.recruitmentsystemproject.Business.FileServices.FileStorageService;
import com.example.recruitmentsystemproject.Business.JobServices.JobCreateService;
import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private JobReadService jobReadService;

    @Autowired
    private JobCreateService jobCreateService;

    @Autowired
    private ApplicationReadService applicationReadService;

    @Autowired
    private ApplicationCreateService applicationCreateService;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private FileStorageService fileStorageService;

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
    public Job employerVacancy() {
        Job job = new Job();
        job.setPostDate(LocalDate.now().toString());
        jobCreateService.saveJob(job);

        return job;
    }

    @PostMapping("/employer/vacancy/save")
    public String saveVacancyDetails(@RequestBody ObjectNode data, BindingResult bindingResult) {

        User user = userReadService.findByEmail("bob123@hotmail.com").get();
        Employer employer = employerReadService.findByUser(user).get();

        Long jobId = data.get("jobId").asLong();
        String title = data.get("title").asText();
        String department = data.get("department").asText();
        String managedBy = data.get("managedBy").asText();
        String location = data.get("location").asText();
        String salary = data.get("salary").asText();
        String activeDate = data.get("activeDate").asText();
        String expiryDate = data.get("expiryDate").asText();
        String startingDate = data.get("startingDate").asText();
        String description = data.get("description").asText();
        String requirements = data.get("requirements").asText();
        String essentialCriteria = data.get("essentialCriteria").asText();
        String desirableCriteria = data.get("desirableCriteria").asText();
        String salaryAndBenefits= data.get("salaryAndBenefits").asText();


        if (bindingResult.hasErrors()){
            System.out.println("Errors" + bindingResult.getFieldError());
            for (ObjectError oe : bindingResult.getAllErrors()) {
                System.out.println(oe);
            }

            return "redirect:/careers/employer/vacancies";

        } else {

            Job job = jobReadService.findById(jobId).get();

            job.setTitle(title);
            job.setEmployer(employer);
            job.setCompany(employer.getCompany());
            job.setDepartment(department);
            job.setManagedBy(managedBy);
            job.setLocation(location);
            job.setSalary(salary);
            job.setActiveDate(activeDate);
            job.setExpiryDate(expiryDate);
            job.setStartingDate(startingDate);
            job.setDescription(description);
            job.setRequirements(requirements);
            job.setEssentialCriteria(essentialCriteria);
            job.setDesirableCriteria(desirableCriteria);
            job.setSalaryAndBenefits(salaryAndBenefits);
            jobCreateService.saveJob(job);


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


    @GetMapping("/employer/vacancy-history")
    public List<Job> employerVacancyHistory() {

        User user = userReadService.findByEmail("bob123@hotmail.com").get();
        Employer employer = employerReadService.findByUser(user).get();
        List<Job> jobs = jobReadService.findByEmployer(employer);

        List applications = new ArrayList();

        for (int i=0; i<jobs.size(); i++){
            List<Application> application = applicationReadService.findByJob(jobs.get(i));
            applications.add(application);
        }

        return jobs;
    }

    @GetMapping("/employer/vacancy-history/{id}")
    public List<Application> getApplicationDetails(@PathVariable Long id) {
        Job job = jobReadService.findById(id).get();
        List<Application> application = applicationReadService.findByJob(job);
        return application;
    }

    @PostMapping("/employer/vacancy-history/accept")
    public String acceptAnApplicant(@RequestBody ObjectNode data) {
        
        Long applicationId = data.get("applicationId").asLong();
        Long applicantId = data.get("applicantId").asLong();

        Applicant applicant = applicantReadService.findById(applicantId).get();
        
        applicationReadService.updateApplicationStatusByApplicantId("Accepted", applicationId, applicant );

        return "redirect:/careers/employer/vacancy-history";
    }

    @PostMapping("/employer/vacancy-history/reject")
    public String rejectAnApplicant(@RequestBody ObjectNode data) {

        Long applicationId = data.get("applicationId").asLong();
        Long applicantId = data.get("applicantId").asLong();

        Applicant applicant = applicantReadService.findById(applicantId).get();

        applicationReadService.updateApplicationStatusByApplicantId("Rejected", applicationId, applicant );

        return "redirect:/careers/employer/vacancy-history";
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType= null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex){
            System.out.println("Could not determine fileType");
        }

        if (contentType==null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

}
