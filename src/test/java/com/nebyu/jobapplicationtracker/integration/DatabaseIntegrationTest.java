package com.nebyu.jobapplicationtracker.integration;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseIntegrationTest {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    // Assuming this is in DatabaseIntegrationTest.java
    @Test
    public void testFindByApplicationDateBetween() {
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);

        // Use the correct repository method
        List<JobApplication> applications = jobApplicationRepository.findByApplicationDateBetween(startDate, endDate);

        assertFalse(applications.isEmpty());
        assertEquals(1, applications.size());
    }
}