package com.example.recruitmentsystemproject.Business.EmployerServices;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;

import java.util.Optional;

public interface EmployerReadService {

    /**
     * Finds Employer by ID
     * @param id
     * @return Employer
     */
    Optional<Employer> findById(Long id);

    /**
     * Finds Employer by User
     * @param user
     * @return Employer
     */
    Optional<Employer> findByUser(User user);
}
