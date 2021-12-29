package com.example.recruitmentsystemproject.Business.ApplicationServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.Application;
import com.example.recruitmentsystemproject.Model.Job;

import java.util.List;
import java.util.Optional;

public interface ApplicationReadService {

    /**
     * Find Application by ID
     * @param id
     * @return Application
     */
    Optional<Application> findById(Long id);

    /**
     * Find Application by Job, Applicant
     * @param job
     * @param applicant
     * @return Application
     */
    Optional<Application> findByJobAndApplicant(Job job, Applicant applicant);

    /**
     * Find list of Applications by Applicant
     * @param applicant
     * @return Application List
     */
    List<Application> findByApplicant(Applicant applicant);

    /**
     * Find list of Applications by Job
     * @param job
     * @return Application List
     */
    List<Application> findByJob(Job job);

    /**
     * Find list of Applications by Application Status
     * @param applicationStatus
     * @return Application list
     */
    List<Application> findByApplicationStatus(String applicationStatus);

    /**
     * Updates Application Status by Status, Application ID, Applicant
     * @param status
     * @param applicationId
     * @param applicant
     */
    void updateApplicationStatusByApplicantId(String status, Long applicationId, Applicant applicant);
}
