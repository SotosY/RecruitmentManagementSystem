package com.example.recruitmentsystemproject.Business.UserServices;

import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Persistence.UserRepositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreateServiceImpl implements UserCreateService{


    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCreateServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Saves User
     * @param user
     */
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.saveUser(user);
    }
}
