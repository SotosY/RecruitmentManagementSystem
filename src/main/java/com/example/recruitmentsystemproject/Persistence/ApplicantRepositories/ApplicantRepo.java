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

    /**
     * Find Applicant by ID
     * @param id
     * @return Applicant
     */
    public Optional<Applicant> findById (Long id){
        return repository.findByApplicantId(id);
    }


    /**
     * Find Applicant by User
     * @param user
     * @return Applicant
     */
    public Optional<Applicant> findByUser(User user){
        return repository.findByUser(user);
    }


    /**
     * Save Applicant
     * @param applicant
     */
    public void saveApplicant(Applicant applicant) {
        repository.save(applicant);
    }
}
