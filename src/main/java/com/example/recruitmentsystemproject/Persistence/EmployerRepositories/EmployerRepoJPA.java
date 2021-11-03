package com.example.recruitmentsystemproject.Persistence.EmployerRepositories;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepoJPA extends JpaRepository<Employer, Long> {

    Optional<Employer> findByEmployerId(Long id);

    Optional<Employer> findByUser(User user);
}
