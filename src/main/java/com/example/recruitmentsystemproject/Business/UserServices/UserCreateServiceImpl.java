package com.example.recruitmentsystemproject.Business.UserServices;

import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Persistence.UserRepositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateServiceImpl implements UserCreateService{

    @Autowired
    private final UserRepo userRepo;

    public UserCreateServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void saveUser(User user) {
        userRepo.saveUser(user);
    }
}
