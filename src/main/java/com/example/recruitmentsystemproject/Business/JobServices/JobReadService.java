package com.example.recruitmentsystemproject.Business.JobServices;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.Job;

import java.util.List;
import java.util.Optional;

public interface JobReadService {

    /**
     * Find Job by ID
     * @param id
     * @return Job
     */
    Optional<Job> findById(Long id);

    /**
     * Finds a list of Jobs by Employer
     * @param employer
     * @return Job list
     */
    List<Job> findByEmployer(Employer employer);

    /**
     * Finds a list of Jobs by Status
     * @param status
     * @return Job list
     */
    List<Job> findByStatus(String status);
}
