package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Persistence.ApplicantRepositories.ApplicantResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicantResumeReadServiceImpl implements ApplicantResumeReadService{

    @Autowired
    private final ApplicantResumeRepo applicantResumeRepo;

    public ApplicantResumeReadServiceImpl(ApplicantResumeRepo applicantResumeRepo) {
        this.applicantResumeRepo = applicantResumeRepo;
    }

    /**
     * Find Applicant Resume by Applicant
     * @param applicant
     * @return Applicant Resume
     */
    @Override
    public Optional<ApplicantResume> findByApplicant(Applicant applicant) {
        return applicantResumeRepo.findByApplicant(applicant);
    }

    /**
     * Find Applicant Resume by ID
     * @param id
     * @return Applicant Resume
     */
    @Override
    public Optional<ApplicantResume> findById(Long id) {
        return applicantResumeRepo.findById(id);
    }
}
