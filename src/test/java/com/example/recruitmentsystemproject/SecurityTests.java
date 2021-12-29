package com.example.recruitmentsystemproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

//    @Test
    public void redirectsToLoginPageWhenNotLoggedIn() throws Exception {
        this.mockMvc
                .perform(get("/careers/applicant/dashboard"))
                .andExpect(status().is(302));
    }

//    @Test
    public void redirectsToLoginPageWhenNotLoggedIn2() throws Exception {
        this.mockMvc
                .perform(get("/careers/employer/dashboard"))
                .andExpect(status().is(302));
    }

//    @Test
    @WithMockUser(username="applicant2", password="pass", roles="APPLICANT")
    public void redirectsToApplicantDashboardWhenLoggedIn() throws Exception {
        this.mockMvc
                .perform(get("/careers/applicant/dashboard"))
                .andExpect(status().is(403));
    }

//    @Test
    @WithMockUser(username="employer2", password="pass", roles="EMPLOYER")
    @WithUserDetails("EMPLOYER")
    public void redirectsToEmployerDashboardWhenLoggedIn() throws Exception {
        this.mockMvc
                .perform(get("/careers/employer/dashboard"))
                .andExpect(status().is(403));
    }
}
