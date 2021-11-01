package com.example.recruitmentsystemproject;

import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserCreateService userCreateService;

    @Test
    public void create_a_user_and_find_by_email(){
        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("APPLICANT");
        userCreateService.saveUser(user);

        User user2 = userReadService.findByEmail("george@outlook.com").get();

        assertNotNull(user);
        Assertions.assertEquals(user2.getEmail(), user.getEmail());
    }
}
