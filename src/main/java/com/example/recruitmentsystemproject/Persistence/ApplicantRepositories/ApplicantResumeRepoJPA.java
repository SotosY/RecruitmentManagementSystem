package com.example.recruitmentsystemproject.Persistence.ApplicantRepositories;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface ApplicantResumeRepoJPA extends JpaRepository<ApplicantResume, Long> {

    /**
     * Find Applicant Resume by id
     * @param id
     * @return applicant resume
     */
    Optional<ApplicantResume> findByResumeId(Long id);

    /**
     * Find applicant resume by applicant
     * @param applicant
     * @return applicant resume
     */
    Optional<ApplicantResume> findByApplicant(Applicant applicant);


}
