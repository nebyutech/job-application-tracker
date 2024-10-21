package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their username.
     */
    User findByUsername(String username);

    /**
     * Find a user by their email.
     */
    Optional<User> findByEmail(String email);
}
