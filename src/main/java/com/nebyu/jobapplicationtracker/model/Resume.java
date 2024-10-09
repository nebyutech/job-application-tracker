package com.nebyu.jobapplicationtracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDate uploadDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters and Setters
}
