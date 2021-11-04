package com.example.recruitmentsystemproject.Persistence.ApplicationRepositories;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.Application;
import com.example.recruitmentsystemproject.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepoJPA extends JpaRepository<Application, Long> {

    Optional<Application> findByApplicationId(Long id);
    List<Application> findByApplicant(Applicant applicant);
    List<Application> findByJob(Job job);
    List<Application> findByApplicationStatus(String applicationStatus);
}
