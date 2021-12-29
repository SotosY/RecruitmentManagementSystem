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

    /**
     * Finds Application by ID
     * @param id
     * @return Application
     */
    @Override
    public Optional<Application> findById(Long id) {
        return applicationRepo.findById(id);
    }


    /**
     * Finds Application by Job, Applicant
     * @param job
     * @param applicant
     * @return Application
     */
    @Override
    public Optional<Application> findByJobAndApplicant(Job job, Applicant applicant) {
        return applicationRepo.findByJobAndApplicant(job, applicant);
    }


    /**
     * Finds list of Applications by Applicant
     * @param applicant
     * @return Application list
     */
    @Override
    public List<Application> findByApplicant(Applicant applicant) {
        return applicationRepo.findByApplicant(applicant);
    }


    /**
     * Finds a list of Applications by Job
     * @param job
     * @return Application list
     */
    @Override
    public List<Application> findByJob(Job job) {
        return applicationRepo.findByJob(job);
    }

    /**
     * Finds list of Applications by Application Status
     * @param applicationStatus
     * @return Application list
     */
    @Override
    public List<Application> findByApplicationStatus(String applicationStatus) {
        return applicationRepo.findByApplicationStatus(applicationStatus);
    }

    /**
     * Updates Application Status by Status, Applicant ID, Applicant
     * @param status
     * @param applicationId
     * @param applicant
     */
    @Override
    public void updateApplicationStatusByApplicantId(String status, Long applicationId, Applicant applicant) {
        applicationRepo.updateApplicationStatusByApplicantId(status, applicationId, applicant);
    }
}
