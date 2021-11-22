package com.example.recruitmentsystemproject;

import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantReadService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantResumeCreateService;
import com.example.recruitmentsystemproject.Business.ApplicantServices.ApplicantResumeReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class ApplicantResumeTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ApplicantReadService applicantReadService;

    @Autowired
    private ApplicantCreateService applicantCreateService;

    @Autowired
    private ApplicantResumeCreateService applicantResumeCreateService;

    @Autowired
    private ApplicantResumeReadService applicantResumeReadService;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserCreateService userCreateService;

    @Test
    public void create_applicant_resume_and_find_by_applicant(){

        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("APPLICANT");
        userCreateService.saveUser(user);

        Applicant applicant = new Applicant();
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

        ApplicantResume applicantResume = new ApplicantResume();
        applicantResume.setApplicant(applicant);
        applicantResume.setExperience("No experience at all");
        applicantResume.setEducation("No education");
        applicantResume.setCv("cv.pdf");
        applicantResume.setCoverLetter("coverletter_pdf");
        applicantResumeCreateService.saveApplicantResume(applicantResume);

        ApplicantResume applicantResume2 = applicantResumeReadService.findByApplicant(applicant).get();
        System.out.println(applicantResume);
        System.out.println(applicantResume2);

        assertNotNull(applicantResume);
        Assertions.assertEquals(applicantResume, applicantResume2);
    }
}
