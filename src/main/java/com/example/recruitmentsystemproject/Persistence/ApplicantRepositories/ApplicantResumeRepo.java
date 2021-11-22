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

    public Optional<ApplicantResume> findById(Long id) {
        return repository.findByResumeId(id);
    }

    public Optional<ApplicantResume> findByApplicant(Applicant applicant){
        return repository.findByApplicant(applicant);
    }

    public void saveApplicantResume(ApplicantResume applicantResume) {
        repository.save(applicantResume);
    }
}
