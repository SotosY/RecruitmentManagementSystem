package com.example.recruitmentsystemproject.Business.JobServices;

import com.example.recruitmentsystemproject.Model.Job;
import com.example.recruitmentsystemproject.Persistence.JobRepositories.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobCreateServiceImpl implements JobCreateService {

    @Autowired
    private final JobRepo jobRepo;

    public JobCreateServiceImpl(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public void saveJob(Job job) {
        jobRepo.saveJob(job);
    }
}
