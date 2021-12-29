package com.example.recruitmentsystemproject.Persistence.EmployerRepositories;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmployerRepo {

    private final EmployerRepoJPA repository;


    public EmployerRepo(EmployerRepoJPA repository) {
        this.repository = repository;
    }


    /**
     * Find Employer by ID
     * @param id
     * @return Employer
     */
    public Optional<Employer> findByEmployerId(Long id){
        return repository.findByEmployerId(id);
    }


    /**
     * Find Employer by User
     * @param user
     * @return Employer
     */
    public Optional<Employer> findByUser(User user){
        return repository.findByUser(user);
    }


    /**
     * Save employer
     * @param employer
     */
    public void saveEmployer(Employer employer){
        repository.save(employer);
    }
}
