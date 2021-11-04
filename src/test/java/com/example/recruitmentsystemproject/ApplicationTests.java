package com.example.recruitmentsystemproject;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationCreateService;
import com.example.recruitmentsystemproject.Business.ApplicationServices.ApplicationReadService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerCreateService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerReadService;
import com.example.recruitmentsystemproject.Business.JobServices.JobCreateService;
import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserCreateService userCreateService;

    @Autowired
    private EmployerReadService employerReadService;

    @Autowired
    private EmployerCreateService employerCreateService;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private JobReadService jobReadService;

    @Autowired
    private JobCreateService jobCreateService;

    @Autowired
    private ApplicationReadService applicationReadService;

    @Autowired
    private ApplicationCreateService applicationCreateService;

    @Test
    public void create_an_application_and_find_by_id() {

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("EMPLOYER");
        userCreateService.saveUser(user);

        User user2 = new User();
        user2.setEmail("george@outlook.com");
        user2.setPassword("sasfsafdepass");
        user2.setRoles("APPLICANT");
        userCreateService.saveUser(user2);

        Employer employer = new Employer();
        employer.setEmployerId(1L);
        employer.setUser(user);
        employer.setFirstName("George");
        employer.setLastName("Ioakim");
        employer.setContactName("George Ioakim");
        employer.setCompanyEmail("georgeIo@cycom.com");
        employer.setBusinessType("Project Manager");
        employer.setTelephoneNumber("233423143");
        employer.setCompanyProfile("Worked for 8 years");
        employer.setAddress("Agiou Oefnoiu");
        employer.setCountry("Cyprus");
        employer.setCity("Nicosia");
        employer.setPostcode("2234");
        employerCreateService.saveEmployer(employer);

        Applicant applicant = new Applicant();
        applicant.setApplicantId(1L);
        applicant.setUser(user);
        applicant.setFirstName("George");
        applicant.setLastName("Ioakim");
        applicant.setPhoneNumber("233423143");
        applicant.setAddress("Agiou Oefnoiu");
        applicant.setCountry("Cyprus");
        applicant.setCity("Nicosia");
        applicant.setPostcode("2234");
        applicant.setDateOfBirth("12/04/1999");
        applicant.setGender("Male");
        applicantCreateService.saveApplicant(applicant);

        Job job = new Job();
        job.setJobId(1L);
        job.setEmployer(employer);
        job.setStatus("Entered");
        job.setTitle("Senior Software Engineer");
        job.setDepartment("Programming");
        job.setManagedBy("Renos Panteli");
        job.setLocation("Nicosia");
        job.setSalary("23000");
        job.setPostDate("15/11/2021");
        job.setActiveDate("20/11/2021");
        job.setExpiryDate("20/12/2021");
        job.setStartingDate("01/01/2022");
        job.setDescription("This is the description");
        job.setRequirements("These are the requirements");
        job.setEssentialCriteria("These are the essential criteria");
        job.setDesirableCriteria("These are the desirable criteria");
        job.setSalaryAndBenefits("These are the salary and benefits");
        jobCreateService.saveJob(job);

        Application application = new Application();
        application.setApplicationId(1L);
        application.setApplicant(applicant);
        application.setJob(job);
        application.setApplyDate("21/09/2020");
        application.setApplicationStatus("Accepted");
        applicationCreateService.saveApplication(application);

        Application application2 = applicationReadService.findById(1L).get();

        assertNotNull(application);
        Assertions.assertEquals(application, application2);
    }

    @Test
    public void create_an_application_and_find_by_applicant() {

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("EMPLOYER");
        userCreateService.saveUser(user);

        User user2 = new User();
        user2.setEmail("george@outlook.com");
        user2.setPassword("sasfsafdepass");
        user2.setRoles("APPLICANT");
        userCreateService.saveUser(user2);

        Employer employer = new Employer();
        employer.setEmployerId(1L);
        employer.setUser(user);
        employer.setFirstName("George");
        employer.setLastName("Ioakim");
        employer.setContactName("George Ioakim");
        employer.setCompanyEmail("georgeIo@cycom.com");
        employer.setBusinessType("Project Manager");
        employer.setTelephoneNumber("233423143");
        employer.setCompanyProfile("Worked for 8 years");
        employer.setAddress("Agiou Oefnoiu");
        employer.setCountry("Cyprus");
        employer.setCity("Nicosia");
        employer.setPostcode("2234");
        employerCreateService.saveEmployer(employer);

        Applicant applicant = new Applicant();
        applicant.setApplicantId(1L);
        applicant.setUser(user);
        applicant.setFirstName("George");
        applicant.setLastName("Ioakim");
        applicant.setPhoneNumber("233423143");
        applicant.setAddress("Agiou Oefnoiu");
        applicant.setCountry("Cyprus");
        applicant.setCity("Nicosia");
        applicant.setPostcode("2234");
        applicant.setDateOfBirth("12/04/1999");
        applicant.setGender("Male");
        applicantCreateService.saveApplicant(applicant);

        Job job = new Job();
        job.setJobId(1L);
        job.setEmployer(employer);
        job.setStatus("Entered");
        job.setTitle("Senior Software Engineer");
        job.setDepartment("Programming");
        job.setManagedBy("Renos Panteli");
        job.setLocation("Nicosia");
        job.setSalary("23000");
        job.setPostDate("15/11/2021");
        job.setActiveDate("20/11/2021");
        job.setExpiryDate("20/12/2021");
        job.setStartingDate("01/01/2022");
        job.setDescription("This is the description");
        job.setRequirements("These are the requirements");
        job.setEssentialCriteria("These are the essential criteria");
        job.setDesirableCriteria("These are the desirable criteria");
        job.setSalaryAndBenefits("These are the salary and benefits");
        jobCreateService.saveJob(job);

        Application application = new Application();
        application.setApplicationId(1L);
        application.setApplicant(applicant);
        application.setJob(job);
        application.setApplyDate("21/09/2020");
        application.setApplicationStatus("Accepted");
        applicationCreateService.saveApplication(application);

        Application application2 = new Application();
        application2.setApplicationId(2L);
        application2.setApplicant(applicant);
        application2.setJob(job);
        application2.setApplyDate("21/09/2020");
        application2.setApplicationStatus("Reject");
        applicationCreateService.saveApplication(application2);

        int numOfApplicationsByApplicant = applicationReadService.findByApplicant(applicant).size();

        assertNotNull(application);
        Assertions.assertEquals(numOfApplicationsByApplicant, 2);
    }

    @Test
    public void create_an_application_and_find_by_job() {

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("EMPLOYER");
        userCreateService.saveUser(user);

        User user2 = new User();
        user2.setEmail("george@outlook.com");
        user2.setPassword("sasfsafdepass");
        user2.setRoles("APPLICANT");
        userCreateService.saveUser(user2);

        Employer employer = new Employer();
        employer.setEmployerId(1L);
        employer.setUser(user);
        employer.setFirstName("George");
        employer.setLastName("Ioakim");
        employer.setContactName("George Ioakim");
        employer.setCompanyEmail("georgeIo@cycom.com");
        employer.setBusinessType("Project Manager");
        employer.setTelephoneNumber("233423143");
        employer.setCompanyProfile("Worked for 8 years");
        employer.setAddress("Agiou Oefnoiu");
        employer.setCountry("Cyprus");
        employer.setCity("Nicosia");
        employer.setPostcode("2234");
        employerCreateService.saveEmployer(employer);

        Applicant applicant = new Applicant();
        applicant.setApplicantId(1L);
        applicant.setUser(user);
        applicant.setFirstName("George");
        applicant.setLastName("Ioakim");
        applicant.setPhoneNumber("233423143");
        applicant.setAddress("Agiou Oefnoiu");
        applicant.setCountry("Cyprus");
        applicant.setCity("Nicosia");
        applicant.setPostcode("2234");
        applicant.setDateOfBirth("12/04/1999");
        applicant.setGender("Male");
        applicantCreateService.saveApplicant(applicant);

        Job job = new Job();
        job.setJobId(1L);
        job.setEmployer(employer);
        job.setStatus("Entered");
        job.setTitle("Senior Software Engineer");
        job.setDepartment("Programming");
        job.setManagedBy("Renos Panteli");
        job.setLocation("Nicosia");
        job.setSalary("23000");
        job.setPostDate("15/11/2021");
        job.setActiveDate("20/11/2021");
        job.setExpiryDate("20/12/2021");
        job.setStartingDate("01/01/2022");
        job.setDescription("This is the description");
        job.setRequirements("These are the requirements");
        job.setEssentialCriteria("These are the essential criteria");
        job.setDesirableCriteria("These are the desirable criteria");
        job.setSalaryAndBenefits("These are the salary and benefits");
        jobCreateService.saveJob(job);

        Job job2 = new Job();
        job2.setJobId(2L);
        job2.setEmployer(employer);
        job2.setStatus("Entered");
        job2.setTitle("Senior Software Engineer");
        job2.setDepartment("Programming");
        job2.setManagedBy("Renos Panteli");
        job2.setLocation("Nicosia");
        job2.setSalary("23000");
        job2.setPostDate("15/11/2021");
        job2.setActiveDate("20/11/2021");
        job2.setExpiryDate("20/12/2021");
        job2.setStartingDate("01/01/2022");
        job2.setDescription("This is the description");
        job2.setRequirements("These are the requirements");
        job2.setEssentialCriteria("These are the essential criteria");
        job2.setDesirableCriteria("These are the desirable criteria");
        job2.setSalaryAndBenefits("These are the salary and benefits");
        jobCreateService.saveJob(job2);

        Application application = new Application();
        application.setApplicationId(1L);
        application.setApplicant(applicant);
        application.setJob(job);
        application.setApplyDate("21/09/2020");
        application.setApplicationStatus("Accepted");
        applicationCreateService.saveApplication(application);

        Application application2 = new Application();
        application2.setApplicationId(2L);
        application2.setApplicant(applicant);
        application2.setJob(job2);
        application2.setApplyDate("21/09/2020");
        application2.setApplicationStatus("Rejected");
        applicationCreateService.saveApplication(application2);

        Application application3 = new Application();
        application3.setApplicationId(3L);
        application3.setApplicant(applicant);
        application3.setJob(job2);
        application3.setApplyDate("21/09/2020");
        application3.setApplicationStatus("Qualified");
        applicationCreateService.saveApplication(application3);

        int numOfApplicationsByJob = applicationReadService.findByJob(job).size();
        int numOfApplicationsByJob2 = applicationReadService.findByJob(job2).size();

        assertNotNull(application);
        Assertions.assertEquals(numOfApplicationsByJob, 1);
        Assertions.assertEquals(numOfApplicationsByJob2, 2);
    }

    @Test
    public void create_an_application_and_find_by_application_status() {

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("EMPLOYER");
        userCreateService.saveUser(user);

        User user2 = new User();
        user2.setEmail("george@outlook.com");
        user2.setPassword("sasfsafdepass");
        user2.setRoles("APPLICANT");
        userCreateService.saveUser(user2);

        Employer employer = new Employer();
        employer.setEmployerId(1L);
        employer.setUser(user);
        employer.setFirstName("George");
        employer.setLastName("Ioakim");
        employer.setContactName("George Ioakim");
        employer.setCompanyEmail("georgeIo@cycom.com");
        employer.setBusinessType("Project Manager");
        employer.setTelephoneNumber("233423143");
        employer.setCompanyProfile("Worked for 8 years");
        employer.setAddress("Agiou Oefnoiu");
        employer.setCountry("Cyprus");
        employer.setCity("Nicosia");
        employer.setPostcode("2234");
        employerCreateService.saveEmployer(employer);

        Applicant applicant = new Applicant();
        applicant.setApplicantId(1L);
        applicant.setUser(user);
        applicant.setFirstName("George");
        applicant.setLastName("Ioakim");
        applicant.setPhoneNumber("233423143");
        applicant.setAddress("Agiou Oefnoiu");
        applicant.setCountry("Cyprus");
        applicant.setCity("Nicosia");
        applicant.setPostcode("2234");
        applicant.setDateOfBirth("12/04/1999");
        applicant.setGender("Male");
        applicantCreateService.saveApplicant(applicant);

        Job job = new Job();
        job.setJobId(1L);
        job.setEmployer(employer);
        job.setStatus("Entered");
        job.setTitle("Senior Software Engineer");
        job.setDepartment("Programming");
        job.setManagedBy("Renos Panteli");
        job.setLocation("Nicosia");
        job.setSalary("23000");
        job.setPostDate("15/11/2021");
        job.setActiveDate("20/11/2021");
        job.setExpiryDate("20/12/2021");
        job.setStartingDate("01/01/2022");
        job.setDescription("This is the description");
        job.setRequirements("These are the requirements");
        job.setEssentialCriteria("These are the essential criteria");
        job.setDesirableCriteria("These are the desirable criteria");
        job.setSalaryAndBenefits("These are the salary and benefits");
        jobCreateService.saveJob(job);

        Job job2 = new Job();
        job2.setJobId(2L);
        job2.setEmployer(employer);
        job2.setStatus("Entered");
        job2.setTitle("Senior Software Engineer");
        job2.setDepartment("Programming");
        job2.setManagedBy("Renos Panteli");
        job2.setLocation("Nicosia");
        job2.setSalary("23000");
        job2.setPostDate("15/11/2021");
        job2.setActiveDate("20/11/2021");
        job2.setExpiryDate("20/12/2021");
        job2.setStartingDate("01/01/2022");
        job2.setDescription("This is the description");
        job2.setRequirements("These are the requirements");
        job2.setEssentialCriteria("These are the essential criteria");
        job2.setDesirableCriteria("These are the desirable criteria");
        job2.setSalaryAndBenefits("These are the salary and benefits");
        jobCreateService.saveJob(job2);

        Application application = new Application();
        application.setApplicationId(1L);
        application.setApplicant(applicant);
        application.setJob(job);
        application.setApplyDate("21/09/2020");
        application.setApplicationStatus("Accepted");
        applicationCreateService.saveApplication(application);

        Application application2 = new Application();
        application2.setApplicationId(2L);
        application2.setApplicant(applicant);
        application2.setJob(job2);
        application2.setApplyDate("21/09/2020");
        application2.setApplicationStatus("Rejected");
        applicationCreateService.saveApplication(application2);

        Application application3 = new Application();
        application3.setApplicationId(3L);
        application3.setApplicant(applicant);
        application3.setJob(job2);
        application3.setApplyDate("21/09/2020");
        application3.setApplicationStatus("Qualified");
        applicationCreateService.saveApplication(application3);

        Application application4 = new Application();
        application4.setApplicationId(4L);
        application4.setApplicant(applicant);
        application4.setJob(job2);
        application4.setApplyDate("21/09/2020");
        application4.setApplicationStatus("Accepted");
        applicationCreateService.saveApplication(application4);

        int numOfApplicationsAccepted = applicationReadService.findByApplicationStatus("Accepted").size();
        int numOfApplicationsRejected = applicationReadService.findByApplicationStatus("Rejected").size();
        int numOfApplicationsQualified = applicationReadService.findByApplicationStatus("Qualified").size();

        assertNotNull(application);
        Assertions.assertEquals(numOfApplicationsAccepted, 2);
        Assertions.assertEquals(numOfApplicationsRejected, 1);
        Assertions.assertEquals(numOfApplicationsQualified, 1);
    }
}
