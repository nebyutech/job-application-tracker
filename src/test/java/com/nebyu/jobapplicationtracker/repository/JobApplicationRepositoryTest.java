package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.repository.JobApplicationRepository;
import com.nebyu.jobapplicationtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class JobApplicationRepositoryTest {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User("testuser", "testuser@example.com", "password");
        userRepository.save(user);
    }

    @Test
    void testFindByUser() {
        // Creating and saving JobApplication with the new constructor
        JobApplication jobApp1 = new JobApplication("Company A", "Developer", "Applied", user);
        jobApplicationRepository.save(jobApp1);

        JobApplication jobApp2 = new JobApplication("Company B", "Designer", "Interviewing", user);
        jobApplicationRepository.save(jobApp2);

        // Fetching job applications by user and verifying the size
        List<JobApplication> applications = jobApplicationRepository.findByUser(user);
        assertNotNull(applications);
        assertEquals(2, applications.size());
    }

}