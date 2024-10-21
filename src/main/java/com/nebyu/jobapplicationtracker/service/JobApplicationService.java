package com.nebyu.jobapplicationtracker.service;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    /**
     * Retrieve all job applications for a specific user.
     */
    public List<JobApplication> getAllJobApplicationsForUser(User user) {
        return jobApplicationRepository.findByUser(user);
    }

    /**
     * Save a job application to the database.
     */
    public JobApplication saveJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    /**
     * Delete a job application by its ID.
     */
    public void deleteJobApplication(Long id) {
        jobApplicationRepository.deleteById(id);
    }

    /**
     * Find a job application by its ID.
     */
    public Optional<JobApplication> findById(Long id) {
        return jobApplicationRepository.findById(id);
    }
}
