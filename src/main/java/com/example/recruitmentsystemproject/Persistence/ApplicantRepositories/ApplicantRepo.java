package com.example.recruitmentsystemproject.Persistence.ApplicantRepositories;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ApplicantRepo {

    private final ApplicantRepoJPA repository;

    @Autowired
    public ApplicantRepo(ApplicantRepoJPA repository) {
        this.repository = repository;
    }

    public Optional<Applicant> findById (Long id){
        return repository.findByApplicantId(id);
    }

    public Optional<Applicant> findByUser(User user){
        return repository.findByUser(user);
    }

    public void saveApplicant(Applicant applicant) {
        repository.save(applicant);
    }
}
