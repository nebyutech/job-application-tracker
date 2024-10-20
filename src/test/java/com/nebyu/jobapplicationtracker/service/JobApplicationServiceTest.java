package com.nebyu.jobapplicationtracker.service;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    private JobApplication testJobApplication;
    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");

        testJobApplication = new JobApplication();
        testJobApplication.setId(1L);
        testJobApplication.setCompany("Test Company");
        testJobApplication.setPosition("Developer");
        testJobApplication.setStatus("Applied");
        testJobApplication.setUser(testUser);
    }

    @Test
    void saveJobApplication_ShouldSaveApplication() {
        when(jobApplicationRepository.save(any(JobApplication.class))).thenReturn(testJobApplication);

        JobApplication savedApplication = jobApplicationService.saveJobApplication(testJobApplication);

        assertNotNull(savedApplication);
        assertEquals("Test Company", savedApplication.getCompany());
        verify(jobApplicationRepository, times(1)).save(testJobApplication);
    }

    @Test
    void getAllJobApplicationsForUser_ShouldReturnApplications() {
        List<JobApplication> applications = new ArrayList<>();
        applications.add(testJobApplication);

        when(jobApplicationRepository.findByUser(testUser)).thenReturn(applications);

        List<JobApplication> foundApplications = jobApplicationService.getAllJobApplicationsForUser(testUser);

        assertNotNull(foundApplications);
        assertFalse(foundApplications.isEmpty());
        assertEquals(1, foundApplications.size());
    }

    @Test
    void deleteJobApplication_ShouldDeleteApplication() {
        doNothing().when(jobApplicationRepository).deleteById(1L);

        jobApplicationService.deleteJobApplication(1L);

        verify(jobApplicationRepository, times(1)).deleteById(1L);
    }
}
