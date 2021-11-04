package com.example.recruitmentsystemproject;

import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerCreateService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerReadService;
import com.example.recruitmentsystemproject.Business.JobServices.JobCreateService;
import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Model.User;
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
public class JobTests {

    @Autowired
    MockMvc mockMvc;

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

    @Test
    public void create_a_job_and_find_by_id() {

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("EMPLOYER");
        userCreateService.saveUser(user);

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

        Job job2 = jobReadService.findById(1L).get();

        assertNotNull(employer);
        Assertions.assertEquals(job, job2);
    }

    @Test
    public void create_a_job_and_find_by_employer() {

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("EMPLOYER");
        userCreateService.saveUser(user);

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

        Job job2 = jobReadService.findByEmployer(employer).get(0);

        assertNotNull(job);
        Assertions.assertEquals(job, job2);
    }

    @Test
    public void create_jobs_and_find_by_status() {

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("EMPLOYER");
        userCreateService.saveUser(user);

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

        Job job3 = new Job();
        job3.setJobId(3L);
        job3.setEmployer(employer);
        job3.setStatus("Entered");
        job3.setTitle("Senior Software Engineer");
        job3.setDepartment("Programming");
        job3.setManagedBy("Renos Panteli");
        job3.setLocation("Nicosia");
        job3.setSalary("23000");
        job3.setPostDate("15/11/2021");
        job3.setActiveDate("20/11/2021");
        job3.setExpiryDate("20/12/2021");
        job3.setStartingDate("01/01/2022");
        job3.setDescription("This is the description");
        job3.setRequirements("These are the requirements");
        job3.setEssentialCriteria("These are the essential criteria");
        job3.setDesirableCriteria("These are the desirable criteria");
        job3.setSalaryAndBenefits("These are the salary and benefits");
        jobCreateService.saveJob(job3);

        Job job4= new Job();
        job4.setJobId(4L);
        job4.setEmployer(employer);
        job4.setStatus("Accepted");
        job4.setTitle("Senior Software Engineer");
        job4.setDepartment("Programming");
        job4.setManagedBy("Renos Panteli");
        job4.setLocation("Nicosia");
        job4.setSalary("23000");
        job4.setPostDate("15/11/2021");
        job4.setActiveDate("20/11/2021");
        job4.setExpiryDate("20/12/2021");
        job4.setStartingDate("01/01/2022");
        job4.setDescription("This is the description");
        job4.setRequirements("These are the requirements");
        job4.setEssentialCriteria("These are the essential criteria");
        job4.setDesirableCriteria("These are the desirable criteria");
        job4.setSalaryAndBenefits("These are the salary and benefits");
        jobCreateService.saveJob(job4);

        int getJobsWhereStatusIsEntered = jobReadService.findByStatus("Entered").size();
        int getJobsWhereStatusIsAccepted= jobReadService.findByStatus("Accepted").size();
        int getJobsWhereStatusIsRejected= jobReadService.findByStatus("Rejected").size();

        Assertions.assertEquals(getJobsWhereStatusIsEntered, 3);
        Assertions.assertEquals(getJobsWhereStatusIsAccepted,1);
        Assertions.assertEquals(getJobsWhereStatusIsRejected, 0);
    }
}
