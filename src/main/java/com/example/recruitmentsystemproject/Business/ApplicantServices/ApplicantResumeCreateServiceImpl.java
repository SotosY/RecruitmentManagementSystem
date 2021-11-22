package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Persistence.ApplicantRepositories.ApplicantResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantResumeCreateServiceImpl implements ApplicantResumeCreateService{

    @Autowired
    private final ApplicantResumeRepo applicantResumeRepo;

    public ApplicantResumeCreateServiceImpl(ApplicantResumeRepo applicantResumeRepo) {
        this.applicantResumeRepo = applicantResumeRepo;
    }

    @Override
    public void saveApplicantResume(ApplicantResume applicantResume) {
        applicantResumeRepo.saveApplicantResume(applicantResume);
    }
}
