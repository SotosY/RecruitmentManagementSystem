package com.example.recruitmentsystemproject.Application.Controllers;

import com.example.recruitmentsystemproject.Business.JobServices.JobReadService;
import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Model.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins={"http://localhost:3000"})
public class UserController {

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private JobReadService jobReadService;


    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("Login page");
    }

    @GetMapping("/login?error")
    public String loginError() {
        return "login";
    }


//    @PostMapping("/login")
//    public String postLogin(@RequestBody ObjectNode data, HttpServletRequest request){
//
//        String email = data.get("username").asText();
//        User user = userReadService.findByEmail(email).get();
//
//        if (user.getRoles()=="APPLICANT"){
//            return "redirect:/applicant/dashboard";
//        } else if (user.getRoles()=="EMPLOYER"){
//            return "redirect:/employer/dashboard";
//        }
//    }

    //    @PostMapping("/login")
//    public ResponseEntity<User> postLogin (@RequestBody User user){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println(authentication);
//        return ResponseEntity.ok(authentication);
//    }

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
