package com.example.recruitmentsystemproject.Business.ApplicationServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.Application;
import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Persistence.ApplicationRepositories.ApplicationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationReadServiceImpl implements ApplicationReadService {

    private final ApplicationRepo applicationRepo;

    public ApplicationReadServiceImpl(ApplicationRepo applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

    @Override
    public Optional<Application> findById(Long id) {
        return applicationRepo.findById(id);
    }

    @Override
    public Optional<Application> findByJobAndApplicant(Job job, Applicant applicant) {
        return applicationRepo.findByJobAndApplicant(job, applicant);
    }

    @Override
    public List<Application> findByApplicant(Applicant applicant) {
        return applicationRepo.findByApplicant(applicant);
    }

    @Override
    public List<Application> findByJob(Job job) {
        return applicationRepo.findByJob(job);
    }

    @Override
    public List<Application> findByApplicationStatus(String applicationStatus) {
        return applicationRepo.findByApplicationStatus(applicationStatus);
    }

    @Override
    public void updateApplicationStatusByApplicantId(String status, Long applicationId, Applicant applicant) {
        applicationRepo.updateApplicationStatusByApplicantId(status, applicationId, applicant);
    }
}
