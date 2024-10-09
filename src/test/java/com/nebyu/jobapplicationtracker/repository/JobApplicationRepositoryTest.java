package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.repository.JobApplicationRepository;
import com.nebyu.jobapplicationtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;




import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class JobApplicationRepositoryTest {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setup() {
        // Create and save a user
        user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setEmail("testuser@example.com");
        userRepository.save(user);

        // Create and save job applications
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
    }

    @Test
    public void testFindByUser() {
        List<JobApplication> applications = jobApplicationRepository.findByUser(user);
        assertNotNull(applications);
        assertEquals(2, applications.size());
    }

    @Test
    public void testFindByStatusAndUser() {
        List<JobApplication> applications = jobApplicationRepository.findByStatusAndUser("Applied", user);
        assertNotNull(applications);
        assertEquals(1, applications.size());
        assertEquals("Company A", applications.get(0).getCompany());
    }
}
