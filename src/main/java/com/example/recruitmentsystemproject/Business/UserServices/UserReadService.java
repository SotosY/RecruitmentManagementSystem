package com.example.recruitmentsystemproject.Business.UserServices;

import com.example.recruitmentsystemproject.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserReadService {

    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);


}
