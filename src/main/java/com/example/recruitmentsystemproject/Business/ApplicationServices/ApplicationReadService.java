package com.example.recruitmentsystemproject.Business.ApplicationServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.Application;
import com.example.recruitmentsystemproject.Model.Job;

import java.util.List;
import java.util.Optional;

public interface ApplicationReadService {

    Optional<Application> findById(Long id);

    List<Application> findByApplicant(Applicant applicant);

    List<Application> findByJob(Job job);

    List<Application> findByApplicationStatus(String applicationStatus);
}
