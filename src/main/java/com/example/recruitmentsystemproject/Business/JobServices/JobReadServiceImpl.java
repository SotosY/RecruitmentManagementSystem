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

    @Override
    public Optional<Job> findById(Long id) {
        return jobRepo.findById(id);
    }

    @Override
    public List<Job> findByEmployer(Employer employer) {
        return jobRepo.findByEmployer(employer);
    }

    @Override
    public List<Job> findByStatus(String status) {
        return jobRepo.findByStatus(status);
    }

}
