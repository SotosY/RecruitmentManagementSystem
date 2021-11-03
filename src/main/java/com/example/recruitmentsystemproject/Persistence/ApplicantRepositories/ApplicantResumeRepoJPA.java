package com.example.recruitmentsystemproject.Persistence.ApplicantRepositories;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface ApplicantResumeRepoJPA extends JpaRepository<ApplicantResume, Long> {

    @Override
    Optional<ApplicantResume> findById(Long id);

    Optional<ApplicantResume> findByApplicant(Applicant applicant);


}
