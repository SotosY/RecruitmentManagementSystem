package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Model.User;

import java.util.Optional;

public interface ApplicantReadService {

    /**
     * Finds Applicant by ID
     * @param id
     * @return Applicant
     */
    Optional<Applicant> findById(Long id);

    /**
     * Finds Applicant by User
     * @param user
     * @return Applicant
     */
    Optional<Applicant> findByUser(User user);
}
