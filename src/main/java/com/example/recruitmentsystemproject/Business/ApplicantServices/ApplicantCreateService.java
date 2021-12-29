package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;

public interface ApplicantCreateService {

    /**
     * Saves Applicant
     * @param applicant
     */
    void saveApplicant(Applicant applicant);
}
