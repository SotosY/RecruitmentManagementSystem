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

    public Optional<Job> findById(Long id){
        return repository.findByJobId(id);
    }

    public List<Job> findByEmployer(Employer employer){
        return repository.findByEmployer(employer);
    }

    public List<Job> findByStatus(String status){
        return repository.findByStatus(status);
    }

    public void saveJob(Job job){
        repository.save(job);
    }
}
