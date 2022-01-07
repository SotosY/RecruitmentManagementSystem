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

import static com.example.recruitmentsystemproject.Application.Controllers.UserController.user;

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

    // POST Request - Registers an Employer
    @PostMapping("/register/e")
    public String registerEmployer(@RequestBody ObjectNode data, HttpServletRequest request, BindingResult bindingResult) {

        String pass = data.get("password").asText();
        String email = data.get("email").asText();
        String firstName = data.get("firstName").asText();
        String lastName = data.get("lastName").asText();
        String company = data.get("company").asText();

        if (bindingResult.hasErrors()){
            System.out.println("Errors" + bindingResult.getFieldError());
            for (ObjectError oe : bindingResult.getAllErrors()) {
                System.out.println(oe);
            }

            return "redirect:/careers/register/e";

        } else {

            User theUser = new User();
            Employer employer = new Employer();

            theUser.setEmail(email);
            theUser.setPassword(pass);
            theUser.setRoles("EMPLOYER");
            userCreateService.saveUser(theUser);

            employer.setUser(theUser);
            employer.setFirstName(firstName);
            employer.setLastName(lastName);
            employer.setCompany(company);
            employerCreateService.saveEmployer(employer);

            user = userReadService.findByEmail(email).get();

            return "redirect:/careers/employer/dashboard";

        }
    }

    // GET Request - Returns to Employer's dashboard
    @GetMapping("/employer/dashboard")
    public ResponseEntity<?> employerDashboard() {

        Optional<Employer> employer = employerReadService.findByUser(user);

        return ResponseEntity.ok(user);
    }

    // GET Request - Returns to Employer's profile
    @GetMapping("/employer/profile")
    public Optional<Employer> employerProfile() {

        Optional<Employer> employer = employerReadService.findByUser(user);

        return employer;
    }

    // POST Request - Saves Employer's profile details
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

            return "redirect:/careers/employer/profile";

        }
    }

    // GET Request - Returns Employer's create new vacancy page
    @GetMapping("/employer/vacancies")
    public Job employerVacancy() {

        Job job = new Job();
        job.setPostDate(LocalDate.now().toString());
        jobCreateService.saveJob(job);

        return job;
    }

    // POST Request - Saves Employer's new vacancy
    @PostMapping("/employer/vacancy/save")
    public String saveVacancyDetails(@RequestBody ObjectNode data, BindingResult bindingResult) {

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

            return "redirect:/careers/employer/profile";

        }
    }

    // GET Request - Returns to Employer's vacancy history page
    @GetMapping("/employer/vacancy-history")
    public List<Job> employerVacancyHistory() {

        Employer employer = employerReadService.findByUser(user).get();
        List<Job> jobs = jobReadService.findByEmployer(employer);

        List applications = new ArrayList();

        for (int i=0; i<jobs.size(); i++){
            List<Application> application = applicationReadService.findByJob(jobs.get(i));
            applications.add(application);
        }

        return jobs;
    }

    // GET Request - Returns to Employer's specific vacancy
    @GetMapping("/employer/vacancy-history/{id}")
    public List<Application> getApplicationDetails(@PathVariable Long id) {
        Job job = jobReadService.findById(id).get();
        List<Application> application = applicationReadService.findByJob(job);
        return application;
    }

    // POST Request - Accepts an applicant
    @PostMapping("/employer/vacancy-history/accept")
    public String acceptAnApplicant(@RequestBody ObjectNode data) {
        
        Long applicationId = data.get("applicationId").asLong();
        Long applicantId = data.get("applicantId").asLong();

        Applicant applicant = applicantReadService.findById(applicantId).get();
        
        applicationReadService.updateApplicationStatusByApplicantId("Accepted", applicationId, applicant );

        return "redirect:/careers/employer/vacancy-history";
    }

    // POST Request - Accepts an applicant
    @PostMapping("/employer/vacancy-history/reject")
    public String rejectAnApplicant(@RequestBody ObjectNode data) {

        Long applicationId = data.get("applicationId").asLong();
        Long applicantId = data.get("applicantId").asLong();

        Applicant applicant = applicantReadService.findById(applicantId).get();

        applicationReadService.updateApplicationStatusByApplicantId("Rejected", applicationId, applicant );

        return "redirect:/careers/employer/vacancy-history";
    }

    // GET Request - Download file page
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
