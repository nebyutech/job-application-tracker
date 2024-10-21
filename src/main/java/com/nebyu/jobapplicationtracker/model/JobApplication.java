package com.nebyu.jobapplicationtracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String position;
    private String status;

    @ManyToOne
    private User user;

    private LocalDate applicationDate;  // LocalDate field for application date

    // No-args constructor
    public JobApplication() {
    }

    // Parameterized constructor
    public JobApplication(String company, String position, String status, User user) {
        this.company = company;
        this.position = position;
        this.status = status;
        this.user = user;
        this.applicationDate = LocalDate.now();  // Set current date by default
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
}