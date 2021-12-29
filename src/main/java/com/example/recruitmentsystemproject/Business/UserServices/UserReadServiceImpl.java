package com.example.recruitmentsystemproject.Business.UserServices;

import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Persistence.UserRepositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReadServiceImpl implements UserReadService {

    @Autowired
    private final UserRepo userRepo;

    public UserReadServiceImpl(UserRepo repository) {
        this.userRepo = repository;
    }


    /**
     * Finds a list of Users
     * @return User list
     */
    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }


    /**
     * Finds a User by ID
     * @param id
     * @return User
     */
    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }


    /**
     * Finds a User by email
     * @param email
     * @return User
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
