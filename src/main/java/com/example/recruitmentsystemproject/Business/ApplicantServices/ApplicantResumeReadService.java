package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;

import java.util.Optional;

public interface ApplicantResumeReadService {

    Optional<ApplicantResume> findByApplicant(Applicant applicant);
    Optional<ApplicantResume> findById(Long id);
}
