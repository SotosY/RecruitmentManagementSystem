package com.example.recruitmentsystemproject.Business.EmployerServices;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;

import java.util.Optional;

public interface EmployerReadService {

    Optional<Employer> findById(Long id);
    Optional<Employer> findByUser(User user);
}
