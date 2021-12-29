package com.example.recruitmentsystemproject.Business.JobServices;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Persistence.JobRepositories.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobReadServiceImpl implements JobReadService{

    @Autowired
    private final JobRepo jobRepo;

    public JobReadServiceImpl(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    /**
     * Find Job by ID
     * @param id
     * @return Job
     */
    @Override
    public Optional<Job> findById(Long id) {
        return jobRepo.findById(id);
    }


    /**
     * Finds a list of Jobs by Employer
     * @param employer
     * @return Job list
     */
    @Override
    public List<Job> findByEmployer(Employer employer) {
        return jobRepo.findByEmployer(employer);
    }


    /**
     * Finds a list of Jobs by Status
     * @param status
     * @return Job list
     */
    @Override
    public List<Job> findByStatus(String status) {
        return jobRepo.findByStatus(status);
    }

}
