package com.example.recruitmentsystemproject;

import com.example.recruitmentsystemproject.Model.File.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class RecruitmentSystemProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentSystemProjectApplication.class, args);
    }

}
