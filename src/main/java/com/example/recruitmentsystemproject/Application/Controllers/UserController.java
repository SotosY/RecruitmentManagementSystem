package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Security.UserDetailsImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins={"http://localhost:3000"}, allowedHeaders = "*", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private JobReadService jobReadService;

    private AuthenticationManager authenticationManager;

    public static User user = new User();

    // GET Request - Returns Login page
    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("login");
    }

    // GET Request - Returns Error Login page
    @GetMapping("/login?error")
    public String loginError() {
        return "login";
    }

    // GET Request - Returns Error Login page
    @GetMapping("/logout")
    public ResponseEntity logout() {
        user = null;
        return ResponseEntity.ok("logout");
    }

    // GET Request - Returns a list of all active jobs
    @GetMapping
    public  List<Job> getAllJobs() {
        return jobReadService.findByStatus("Active");
    }
}
