package com.example.recruitmentsystemproject.Business.JobServices;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.Job;

import java.util.List;
import java.util.Optional;

public interface JobReadService {

    Optional<Job> findById(Long id);
    List<Job> findByEmployer(Employer employer);
    List<Job> findByStatus(String status);
}
