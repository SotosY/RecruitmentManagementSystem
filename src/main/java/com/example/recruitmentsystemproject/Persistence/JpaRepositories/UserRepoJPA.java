package com.example.recruitmentsystemproject.Persistence.JpaRepositories;

import com.example.recruitmentsystemproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepoJPA extends JpaRepository<User, Long> {

    Optional<User> findByUserID (Long id);
    Optional<User> findFirstByEmail(String email);
    List<User> findAll();
}
