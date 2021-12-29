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

    /**
     * Finds Application by ID
     * @param id
     * @return Application
     */
    public Optional<Application> findById(Long id){
        return repository.findByApplicationId(id);
    }


    /**
     * Finds Application by Job, Applicant
     * @param job
     * @param applicant
     * @return Application
     */
    public Optional<Application> findByJobAndApplicant(Job job, Applicant applicant){
        return repository.findByJobAndApplicant(job, applicant);
    }


    /**
     * Finds a list of Applications by Applicant
     * @param applicant
     * @return Application list
     */
    public List<Application> findByApplicant(Applicant applicant){
        return repository.findByApplicant(applicant);
    }


    /**
     * Finds a list of Applications by Job
     * @param job
     * @return Application list
     */
    public List<Application> findByJob(Job job){
        return repository.findByJob(job);
    }


    /**
     * Finds a list of Application by Application status
     * @param applicationStatus
     * @return Application list
     */
    public List<Application> findByApplicationStatus(String applicationStatus){
        return repository.findByApplicationStatus(applicationStatus);
    }


    /**
     * Saves Application
     * @param application
     */
    public void saveApplication(Application application){
        repository.save(application);
    }


    /**
     * Updates Application status by Status, Application ID, Applicant
     * @param status
     * @param applicationId
     * @param applicant
     */
    public void updateApplicationStatusByApplicantId(String status, Long applicationId, Applicant applicant) {
        repository.updateStatusByApplicant(status, applicationId, applicant);
    }
}
