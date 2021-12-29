package com.example.recruitmentsystemproject.Persistence.JobRepositories;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.Job;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JobRepo {

    private final JobRepoJPA repository;

    public JobRepo(JobRepoJPA repository) {
        this.repository = repository;
    }


    /**
     * Find a job by id
     * @param id
     * @return job
     */
    public Optional<Job> findById(Long id){
        return repository.findByJobId(id);
    }


    /**
     * Get a list of jobs by employer
     * @param employer
     * @return list of jobs
     */
    public List<Job> findByEmployer(Employer employer){
        return repository.findByEmployer(employer);
    }


    /**
     * Get a list of jobs by status
     * @param status
     * @return list of jobs
     */
    public List<Job> findByStatus(String status){
        return repository.findByStatus(status);
    }


    /**
     * Saves Job
     * @param job
     */
    public void saveJob(Job job){
        repository.save(job);
    }
}
