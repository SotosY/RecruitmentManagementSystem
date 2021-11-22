package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Model.User;

import java.util.Optional;

public interface ApplicantReadService {

    Optional<Applicant> findById(Long id);
    Optional<Applicant> findByUser(User user);
}
