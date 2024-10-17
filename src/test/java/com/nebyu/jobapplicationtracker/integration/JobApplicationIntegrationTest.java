package com.nebyu.jobapplicationtracker.integration;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.repository.JobApplicationRepository;
import com.nebyu.jobapplicationtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class JobApplicationIntegrationTest {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private JobApplication testJobApplication;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setEmail("testuser@example.com");
        userRepository.save(testUser);

        testJobApplication = new JobApplication();
        testJobApplication.setCompany("Test Company");
        testJobApplication.setPosition("Developer");
        testJobApplication.setStatus("Applied");
        testJobApplication.setUser(testUser);
        testJobApplication.setApplicationDate(LocalDate.now());

        jobApplicationRepository.save(testJobApplication);
    }

    @Test
    public void testCreateAndRetrieveJobApplication() {
        List<JobApplication> applications = jobApplicationRepository.findByUser(testUser);
        assertFalse(applications.isEmpty());
        assertEquals(1, applications.size());
        assertEquals("Test Company", applications.get(0).getCompany());
    }

    @Test
    public void testDeleteJobApplication() {
        jobApplicationRepository.deleteById(testJobApplication.getId());
        List<JobApplication> applications = jobApplicationRepository.findByUser(testUser);
        assertTrue(applications.isEmpty());
    }
}
