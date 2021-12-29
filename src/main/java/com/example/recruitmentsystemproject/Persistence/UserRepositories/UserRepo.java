package com.example.recruitmentsystemproject.Persistence.UserRepositories;

import com.example.recruitmentsystemproject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo {

    private final UserRepoJPA repository;

    @Autowired
    public UserRepo(UserRepoJPA repository) {
        this.repository = repository;
    }

    /**
     * Find a User by id
     * @param id
     * @return user
     */
    public Optional<User> findById(Long id) {
        return repository.findByUserId(id);
    }


    /**
     * Find a User by email
     * @param email
     * @return user
     */
    public Optional<User> findByEmail(String email) {
        return repository.findFirstByEmail(email);
    }


    /**
     * Find a list of all users
     * @return a list of all users
     */
    public List<User> findAll() {
        return repository.findAll();
    }


    /**
     * Saves a user
     * @param user
     */
    public void saveUser(User user) {
        repository.save(user);
    }

}
