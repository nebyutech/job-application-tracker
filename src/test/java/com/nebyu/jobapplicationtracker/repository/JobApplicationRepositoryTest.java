package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");
        userRepository.save(user);
    }

    @Test
    void testFindByUser() {
        // Create and save JobApplications linked to user
        JobApplication jobApp1 = new JobApplication();
        jobApp1.setCompany("Company A");
        jobApp1.setPosition("Developer");
        jobApp1.setStatus("Applied");
        jobApp1.setUser(user);
        jobApplicationRepository.save(jobApp1);

        JobApplication jobApp2 = new JobApplication();
        jobApp2.setCompany("Company B");
        jobApp2.setPosition("Designer");
        jobApp2.setStatus("Interviewing");
        jobApp2.setUser(user);
        jobApplicationRepository.save(jobApp2);

        // Fetch job applications by user
        List<JobApplication> applications = jobApplicationRepository.findByUser(user);
        assertNotNull(applications);
        assertEquals(2, applications.size());
    }
}
