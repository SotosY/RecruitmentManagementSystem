package com.example.recruitmentsystemproject.Persistence.EmployerRepositories;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepoJPA extends JpaRepository<Employer, Long> {

    /**
     * Find an employer by id
     * @param id
     * @return employer
     */
    Optional<Employer> findByEmployerId(Long id);

    /**
     * Find an employer by user
     * @param user
     * @return employer
     */
    Optional<Employer> findByUser(User user);
}
