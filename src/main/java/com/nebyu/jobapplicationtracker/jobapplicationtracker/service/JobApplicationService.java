package com.nebyu.jobapplicationtracker.jobapplicationtracker.service;

import com.nebyu.jobapplicationtracker.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.jobapplicationtracker.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public List<JobApplication> getAllJobApplicationsForUser(User user) {
        return jobApplicationRepository.findByUser(user);
    }

    public JobApplication saveJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    public void deleteJobApplication(Long id) {
        jobApplicationRepository.deleteById(id);
    }
}
