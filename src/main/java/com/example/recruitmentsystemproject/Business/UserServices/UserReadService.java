package com.example.recruitmentsystemproject.Business.UserServices;

import com.example.recruitmentsystemproject.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserReadService {

    /**
     * Finds a list of Users
     * @return User list
     */
    List<User> findAll();


    /**
     * Finds a User by ID
     * @param id
     * @return User
     */
    Optional<User> findById(Long id);


    /**
     * Finds a User by email
     * @param email
     * @return User
     */
    Optional<User> findByEmail(String email);

}
