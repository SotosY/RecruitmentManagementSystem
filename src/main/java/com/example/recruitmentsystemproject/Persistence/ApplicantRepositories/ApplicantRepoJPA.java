package com.example.recruitmentsystemproject.Persistence.ApplicantRepositories;

import com.example.recruitmentsystemproject.Model.Applicant;
import com.example.recruitmentsystemproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepoJPA extends JpaRepository<Applicant, Long> {

    Optional<Applicant> findByApplicantId(Long id);

    Optional<Applicant> findByUser(User user);
}
