package com.example.recruitmentsystemproject.Persistence.ApplicantRepositories;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ApplicantResumeRepo {

    private final ApplicantResumeRepoJPA repository;

    public ApplicantResumeRepo(ApplicantResumeRepoJPA repository) {
        this.repository = repository;
    }


    /**
     * Find Applicant Resume by ID
     * @param id
     * @return Applicant Resume
     */
    public Optional<ApplicantResume> findById(Long id) {
        return repository.findByResumeId(id);
    }


    /**
     * Find Applicant Resume by Applicant
     * @param applicant
     * @return
     */
    public Optional<ApplicantResume> findByApplicant(Applicant applicant){
        return repository.findByApplicant(applicant);
    }


    /**
     * Save Applicant Resume
     * @param applicantResume
     */
    public void saveApplicantResume(ApplicantResume applicantResume) {
        repository.save(applicantResume);
    }
}
