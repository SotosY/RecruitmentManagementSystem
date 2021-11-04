package com.example.recruitmentsystemproject;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class ApplicantTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserCreateService userCreateService;

    @Test
    public void create_an_applicant_and_find_by_id(){

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("APPLICANT");
        userCreateService.saveUser(user);

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

        Applicant applicant2 = applicantReadService.findById(1L).get();

        assertNotNull(applicant);
        Assertions.assertEquals(applicant, applicant2);
    }

    @Test
    public void create_an_applicant_and_find_by_user(){

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("APPLICANT");
        userCreateService.saveUser(user);

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

        Applicant applicant2 = applicantReadService.findByUser(user).get();

        assertNotNull(applicant);
        Assertions.assertEquals(applicant, applicant2);
    }

}
