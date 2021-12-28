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

    @GetMapping("/login")
    public ResponseEntity<?> login() {



        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
////
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
//        System.out.println(authentication.getName());
        System.out.println(principal);
        System.out.println(session);
//        System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
        Optional<User> user = userReadService.findByEmail(((UserDetailsImpl)principal).getUsername());

        return ResponseEntity.ok(user);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> registerApplicant(@RequestBody ObjectNode data, HttpServletRequest request, BindingResult bindingResult) {
//
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/login?error")
    public String loginError() {
        return "login";
    }

//
    @PostMapping("/login")
    public String postLogin(@RequestBody ObjectNode data, HttpServletRequest request){

        String email = data.get("username").asText();
        User user = userReadService.findByEmail(email).get();

        if (user.getRoles()=="APPLICANT"){
            return "redirect:/applicant/dashboard";
        } else if (user.getRoles()=="EMPLOYER"){
            return "redirect:/employer/dashboard";
        }
        return "hi";
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> postLogin (HttpServletRequest request){
//
//        System.out.println(request.getUserPrincipal());
//
////
////        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
////                email, pass
////        ));
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Optional<User> theUser = userReadService.findByEmail(((UserDetailsImpl)principal).getUsername());
//
//
//        System.out.println(authentication);
//        return ResponseEntity.ok(theUser);
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
