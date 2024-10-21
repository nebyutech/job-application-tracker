package com.nebyu.jobapplicationtracker.controller;

import com.nebyu.jobapplicationtracker.model.User;

import com.nebyu.jobapplicationtracker.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController  // Ensure this annotation is present
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {
        Optional<User> existingUser = Optional.ofNullable(userService.findByUsername(user.getUsername()));
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already taken."));
        }

        User registeredUser = userService.registerUser(user);  // Register user and get the saved entity

        // Create a response map with the userId and message
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully.");
        response.put("userId", registeredUser.getId());  // Include userId in the response

        return ResponseEntity.ok(response);  // Return 200 OK with the response
    }






    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        Optional<User> existingUser = Optional.ofNullable(userService.findByUsername(user.getUsername()));
        if (existingUser.isPresent() &&
                new BCryptPasswordEncoder().matches(user.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.ok("Login successful.");
        }
        return ResponseEntity.badRequest().body("Invalid username or password.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserEmail(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            userService.updateUserEmail(id, updatedUser.getEmail());
            return ResponseEntity.ok("User updated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("User not found.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user: " + e.getMessage());
        }
    }

}