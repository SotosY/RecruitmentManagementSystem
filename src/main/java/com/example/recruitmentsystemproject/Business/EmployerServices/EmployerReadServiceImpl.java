package com.example.recruitmentsystemproject.Business.EmployerServices;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Persistence.EmployerRepositories.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerReadServiceImpl implements EmployerReadService {

    private final EmployerRepo employerRepo;

    public EmployerReadServiceImpl(EmployerRepo employerRepo) {
        this.employerRepo = employerRepo;
    }

    /**
     * Find Employer by ID
     * @param id
     * @return Employer
     */
    @Override
    public Optional<Employer> findById(Long id) {
        return employerRepo.findByEmployerId(id);
    }


    /**
     * Find Employer by User
     * @param user
     * @return Employer
     */
    @Override
    public Optional<Employer> findByUser(User user) {
        return employerRepo.findByUser(user);
    }
}
