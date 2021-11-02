package com.example.recruitmentsystemproject.Persistence.UserRepositories;

import com.example.recruitmentsystemproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepoJPA extends JpaRepository<User, Long> {

    /**
     * Find a User by id
     * @param id
     * @return user
     */
    Optional<User> findByUserId (Long id);

    /**
     * Find a User by email
     * @param email
     * @return user
     */
    Optional<User> findFirstByEmail(String email);

    /**
     * Find a list of all users
     * @return a list of all users
     */
    List<User> findAll();
}
