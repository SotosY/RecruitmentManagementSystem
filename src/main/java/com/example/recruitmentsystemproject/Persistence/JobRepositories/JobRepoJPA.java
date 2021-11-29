package com.example.recruitmentsystemproject.Persistence.JobRepositories;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepoJPA extends JpaRepository<Job, Long> {

    /**
     * Find a job by id
     * @param id
     * @return job
     */
    Optional<Job> findByJobId(Long id);

    /**
     * Get a list of jobs by employer
     * @param employer
     * @return list of jobs
     */
    List<Job> findByEmployer(Employer employer);

    /**
     * Get a list of jobs by status
     * @param status
     * @return list of jobs
     */
    List<Job> findByStatus(String status);

}
