package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;

public interface ApplicantCreateService {

    void saveApplicant(Applicant applicant);

    void saveApplicantResume(ApplicantResume applicantResume);
}
