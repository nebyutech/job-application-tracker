package com.nebyu.jobapplicationtracker.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.jobapplicationtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
