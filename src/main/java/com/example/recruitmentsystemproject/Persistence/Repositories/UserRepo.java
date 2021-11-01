package com.example.recruitmentsystemproject.Persistence.Repositories;

import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Persistence.JpaRepositories.UserRepoJPA;
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
        return repository.findByUserID(id);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findFirstByEmail(email);
    }

}
