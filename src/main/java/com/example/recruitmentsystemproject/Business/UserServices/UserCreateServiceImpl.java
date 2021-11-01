package com.example.recruitmentsystemproject.Business.UserServices;

import com.example.recruitmentsystemproject.Model.User;
import com.example.recruitmentsystemproject.Persistence.Repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserCreateServiceImpl implements UserCreateService{

    private final UserRepo repository;

    public UserCreateServiceImpl(UserRepo repository) {
        this.repository = repository;
    }

    @Override
    public void saveUser(User user) {
        repository.saveUser(user);
    }
}
