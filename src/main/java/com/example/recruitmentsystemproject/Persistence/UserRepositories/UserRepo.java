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

    public List<User> findAll() {
        return repository.findAll();
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public Optional<User> findById(Long id) {
        return repository.findByUserId(id);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findFirstByEmail(email);
    }

}
