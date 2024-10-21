package com.nebyu.jobapplicationtracker.service;

import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Register a new user by encrypting their password before saving.
     */
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encrypt password
        return userRepository.save(user);  // Save user to the repository
    }

    /**
     * Find a user by their username.
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find a user by their ID.
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Update a user's email.
     */
    public void updateUserEmail(Long id, String newEmail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEmail(newEmail);
        userRepository.save(user);  // Save updated user
    }

    public boolean loginUser(String username, String password123) {
        return false;
    }
}
