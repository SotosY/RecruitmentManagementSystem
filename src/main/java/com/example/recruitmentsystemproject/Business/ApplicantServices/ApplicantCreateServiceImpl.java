package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Persistence.ApplicantRepositories.ApplicantRepo;
import com.example.recruitmentsystemproject.Persistence.ApplicantRepositories.ApplicantResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantCreateServiceImpl implements ApplicantCreateService {

    @Autowired
    private final ApplicantRepo applicantRepo;


    public ApplicantCreateServiceImpl(ApplicantRepo applicantRepo) {
        this.applicantRepo = applicantRepo;
    }

    /**
     * Saves Applicant
     * @param applicant
     */
    @Override
    public void saveApplicant(Applicant applicant) {
        applicantRepo.saveApplicant(applicant);
    }

}
