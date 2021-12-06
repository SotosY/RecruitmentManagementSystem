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

    Optional<Application> findByApplicationId(Long id);
    Optional<Application> findByJobAndApplicant(Job job, Applicant applicant);
    List<Application> findByApplicant(Applicant applicant);
    List<Application> findByJob(Job job);
    List<Application> findByApplicationStatus(String applicationStatus);

    @Transactional
    @Modifying
    @Query("UPDATE #{#entityName} SET applicationStatus=?1 WHERE applicationId=?2 AND applicant=?3")
    void updateStatusByApplicant(String status, Long applicationID, Applicant applicant);
}
