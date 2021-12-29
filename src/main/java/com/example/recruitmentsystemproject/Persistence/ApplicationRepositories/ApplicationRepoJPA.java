package com.example.recruitmentsystemproject.Persistence.ApplicationRepositories;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.Application;
import com.example.recruitmentsystemproject.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepoJPA extends JpaRepository<Application, Long> {

    /**
     * Finds Application by ID
     * @param id
     * @return Application
     */
    Optional<Application> findByApplicationId(Long id);


    /**
     * Finds Application by Job, Applicant
     * @param job
     * @param applicant
     * @return Application
     */
    Optional<Application> findByJobAndApplicant(Job job, Applicant applicant);


    /**
     * Finds a list of Applications by Applicant
     * @param applicant
     * @return Application list
     */
    List<Application> findByApplicant(Applicant applicant);


    /**
     * Finds a list of Applications by Job
     * @param job
     * @return Application list
     */
    List<Application> findByJob(Job job);


    /**
     * Finds a list of Application by Application status
     * @param applicationStatus
     * @return Application list
     */
    List<Application> findByApplicationStatus(String applicationStatus);


    /**
     * Updates Application status by Status, Application ID, Applicant
     * @param status
     * @param applicationID
     * @param applicant
     */
    @Transactional
    @Modifying
    @Query("UPDATE #{#entityName} SET applicationStatus=?1 WHERE applicationId=?2 AND applicant=?3")
    void updateStatusByApplicant(String status, Long applicationID, Applicant applicant);
}
