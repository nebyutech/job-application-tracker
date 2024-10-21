package com.nebyu.jobapplicationtracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key for job applications

    private String company;  // Name of the company
    private String position;  // Job position
    private String status;  // Application status (e.g., Applied, Interviewing, Offer)

    @ManyToOne
    private User user;  // Association with the User who owns the application

    private LocalDate applicationDate;  // Date the application was made

    // No-args constructor required by JPA
    public JobApplication() {}

    // Constructor for creating new JobApplication instances
    public JobApplication(String company, String position, String status, User user) {
        this.company = company;
        this.position = position;
        this.status = status;
        this.user = user;
        this.applicationDate = LocalDate.now();  // Set the application date to the current date
    }

    // Getters and Setters for all fields
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
