package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;

import java.util.Optional;

public interface ApplicantResumeReadService {

    /**
     * Find Applicant Resume by Applicant
     * @param applicant
     * @return Applicant Resume
     */
    Optional<ApplicantResume> findByApplicant(Applicant applicant);

    /**
     * Find Applicant Resume by ID
     * @param id
     * @return Applicant Resume
     */
    Optional<ApplicantResume> findById(Long id);
}
