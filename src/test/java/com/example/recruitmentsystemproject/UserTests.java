package com.example.recruitmentsystemproject;

import com.example.recruitmentsystemproject.Business.UserServices.UserCreateService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
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

    @Test
    public void create_a_user_and_find_by_id(){
        User user = new User();
        user.setEmail("george@outlook.com");
        user.setPassword("sasfsafdepass");
        user.setRoles("APPLICANT");
        userCreateService.saveUser(user);

        Long id = user.getUserId();
        User user2 = userReadService.findById(id).get();

        assertNotNull(user);
        Assertions.assertEquals(user, user2);
    }

    @Test
    public void create_5_users_and_find_all(){

        Integer allUsersBefore = userReadService.findAll().size();

        User user_1 = new User("george@outlook.com","kkjsasfsafhjkkdepass",true, "APPLICANT");
        User user_2 = new User("sotos@outlook.com","sasjhgfsafdsaddepass",true, "APPLICANT");
        User user_3 = new User("chris@outlook.com","sasfsadgghysafdepass",true, "APPLICANT");
        User user_4 = new User("hampis@outlook.com","sasfsafdepass",true, "APPLICANT");
        User user_5 = new User("dionisis@outlook.com","sasfsjkliafdepass",true, "APPLICANT");

        userCreateService.saveUser(user_1);
        userCreateService.saveUser(user_2);
        userCreateService.saveUser(user_3);
        userCreateService.saveUser(user_4);
        userCreateService.saveUser(user_5);

        Integer allUsersSize = userReadService.findAll().size();

        Assertions.assertEquals(allUsersSize, allUsersBefore + 5);
    }
}
