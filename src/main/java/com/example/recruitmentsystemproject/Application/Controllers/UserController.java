package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private JobReadService jobReadService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userReadService.findAll();
    }

    @GetMapping
    public  List<Job> getAllJobs() {
        return jobReadService.findByStatus("Active");
    }
}
