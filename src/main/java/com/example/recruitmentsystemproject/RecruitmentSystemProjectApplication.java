package com.example.recruitmentsystemproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RecruitmentSystemProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentSystemProjectApplication.class, args);
    }

}
