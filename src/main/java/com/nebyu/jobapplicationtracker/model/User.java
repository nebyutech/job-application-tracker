package com.nebyu.jobapplicationtracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")  // Define the users table in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key for users

    @Column(nullable = false)
    private String username;  // Username of the user

    @Column(nullable = false)
    private String password;  // Encrypted password

    @Column(nullable = false, unique = true)
    private String email;  // Email of the user (must be unique)

    // No-args constructor required by JPA
    public User() {}

    public User(String testuser, String mail, String password123) {

    }

    public User(String testuser, String password123) {

    }

    // Getters and Setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
