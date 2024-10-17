package com.nebyu.jobapplicationtracker.controller;

import com.nebyu.jobapplicationtracker.model.User;

import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController  // Ensure this annotation is present
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        Optional<User> existingUser = Optional.ofNullable(userService.findByUsername(user.getUsername()));
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }

        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully.");
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
}