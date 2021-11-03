package com.example.recruitmentsystemproject;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerCreateService;
import com.example.recruitmentsystemproject.Business.EmployerServices.EmployerReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployerTests {


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

    @Test
    public void create_an_employer_and_find_by_id() {

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

        Employer employer2 = employerReadService.findById(1L).get();

        assertNotNull(employer);
        Assertions.assertEquals(employer, employer2);
    }

    @Test
    public void create_an_employer_and_find_by_user() {

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

        Employer employer2 = employerReadService.findByUser(user).get();

        assertNotNull(employer);
        Assertions.assertEquals(employer, employer2);
    }
}
