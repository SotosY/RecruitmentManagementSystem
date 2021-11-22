package com.example.recruitmentsystemproject.Business.ApplicantServices;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.ApplicantResume;
import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Persistence.ApplicantRepositories.ApplicantRepo;
import com.example.recruitmentsystemproject.Persistence.ApplicantRepositories.ApplicantResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicantReadServiceImpl implements ApplicantReadService {

    @Autowired
    private final ApplicantRepo applicantRepo;

    public ApplicantReadServiceImpl(ApplicantRepo applicantRepo) {
        this.applicantRepo = applicantRepo;
    }

    @Override
    public Optional<Applicant> findById(Long id) {
        return applicantRepo.findById(id);
    }

    @Override
    public Optional<Applicant> findByUser(User user) {
        return applicantRepo.findByUser(user);
    }

}
