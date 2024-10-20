//package com.nebyu.jobapplicationtracker.repository;
//
//import com.nebyu.jobapplicationtracker.model.JobApplication;
//import com.nebyu.jobapplicationtracker.model.User;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional
//class JobApplicationRepositoryTest {
//
//    @Autowired
//    private JobApplicationRepository jobApplicationRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        jobApplicationRepository.deleteAll();
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void testFindByStatusAndUser() {
//        User testUser = new User();
//        testUser.setUsername("testuser" + System.currentTimeMillis());
//        testUser.setEmail("test" + System.currentTimeMillis() + "@example.com");
//        testUser.setPassword("password123");
//
//        User savedUser = userRepository.save(testUser);
//        assertNotNull(savedUser.getId());
//
//        JobApplication jobApp = new JobApplication("Google", "Developer", "Applied", savedUser);
//        jobApplicationRepository.save(jobApp);
//
//        List<JobApplication> applications = jobApplicationRepository.findByStatusAndUser("Applied", savedUser);
//        assertFalse(applications.isEmpty());
//        assertEquals("Google", applications.get(0).getCompany());
//    }
//}


package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobApplicationRepositoryTest {

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
    public void testFindByUser() {
        List<JobApplication> applications = jobApplicationRepository.findByUser(testUser);
        assertFalse(applications.isEmpty());
        assertEquals(1, applications.size());
        assertEquals("Test Company", applications.get(0).getCompany());
    }

    @Test
    public void testFindByStatusAndUser() {
        List<JobApplication> applications = jobApplicationRepository.findByStatusAndUser("Applied", testUser);
        assertFalse(applications.isEmpty());
        assertEquals("Developer", applications.get(0).getPosition());
    }

    @Test
    public void testFindByApplicationDateBetween() {
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);

        List<JobApplication> applications = jobApplicationRepository.findByApplicationDateBetween(startDate, endDate);
        assertFalse(applications.isEmpty());
        assertEquals(1, applications.size());
    }

    @Test
    public void testCountJobApplicationsByUser() {
        int count = jobApplicationRepository.countJobApplicationsByUser(testUser);
        assertEquals(1, count);
    }
}
