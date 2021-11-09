package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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

    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, User user, BindingResult bindingResult){
        String pass = user.getPassword();
        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            request.login(user.getEmail(), pass);
        } catch (ServletException e) {
            System.out.println(e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Login was not possible");
        }

        return "redirect:/applicant/dashboard";
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
