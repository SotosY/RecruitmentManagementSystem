package com.example.recruitmentsystemproject.Persistence.ApplicationRepositories;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.Application;
import com.example.recruitmentsystemproject.Model.Job;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationRepo {

    private final ApplicationRepoJPA repository;

    public ApplicationRepo(ApplicationRepoJPA repository) {
        this.repository = repository;
    }

    public Optional<Application> findById(Long id){
        return repository.findByApplicationId(id);
    }

    public Optional<Application> findByJobAndApplicant(Job job, Applicant applicant){
        return repository.findByJobAndApplicant(job, applicant);
    }

    public List<Application> findByApplicant(Applicant applicant){
        return repository.findByApplicant(applicant);
    }

    public List<Application> findByJob(Job job){
        return repository.findByJob(job);
    }

    public List<Application> findByApplicationStatus(String applicationStatus){
        return repository.findByApplicationStatus(applicationStatus);
    }

    public void saveApplication(Application application){
        repository.save(application);
    }

    public void updateApplicationStatusByApplicantId(String status, Long applicationId, Applicant applicant) {
        repository.updateStatusByApplicant(status, applicationId, applicant);
    }
}
