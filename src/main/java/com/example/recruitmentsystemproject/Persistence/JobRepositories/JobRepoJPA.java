package com.example.recruitmentsystemproject.Persistence.JobRepositories;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepoJPA extends JpaRepository<Job, Long> {

    Optional<Job> findByJobId(Long id);

    List<Job> findByEmployer(Employer employer);

    List<Job> findByStatus(String status);

}
