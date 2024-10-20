package com.nebyu.jobapplicationtracker.controller;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.service.JobApplicationService;
import com.nebyu.jobapplicationtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/api/applications")
//public class JobApplicationController {
//
//    @Autowired
//    private JobApplicationService jobApplicationService;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createJobApplication(@RequestParam Long userId, @RequestBody JobApplication jobApplication) {
//        Optional<User> user = userService.findById(userId);
//
//        if (user.isPresent()) {
//            jobApplication.setUser(user.get());
//            jobApplicationService.saveJobApplication(jobApplication);
//            return ResponseEntity.ok("Job application created successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("User not found.");
//        }
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<JobApplication>> getAllApplicationsForUser(@PathVariable Long userId) {
//        Optional<User> user = userService.findById(userId);
//        if (user.isPresent()) {
//            List<JobApplication> applications = jobApplicationService.getAllJobApplicationsForUser(user.get());
//            return ResponseEntity.ok(applications);
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteJobApplication(@PathVariable Long id) {
//        jobApplicationService.deleteJobApplication(id);
//        return ResponseEntity.ok("Job application deleted successfully.");
//    }
//}


import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.service.JobApplicationService;
import com.nebyu.jobapplicationtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private UserService userService;

    // Create a new job application
    @PostMapping("/create")
    public ResponseEntity<String> createJobApplication(@RequestParam Long userId, @RequestBody JobApplication jobApplication) {
        Optional<User> user = userService.findById(userId);

        if (user.isPresent()) {
            jobApplication.setUser(user.get());
            jobApplicationService.saveJobApplication(jobApplication);
            return ResponseEntity.ok("Job application created successfully.");
        } else {
            return ResponseEntity.badRequest().body("User not found.");
        }
    }

    // Get all job applications for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobApplication>> getAllApplicationsForUser(@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            List<JobApplication> applications = jobApplicationService.getAllJobApplicationsForUser(user.get());
            return ResponseEntity.ok(applications);
        }
        return ResponseEntity.badRequest().build();
    }

    // Delete a specific job application by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobApplication(@PathVariable Long id) {
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.ok("Job application deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobApplication(@PathVariable Long id, @RequestBody JobApplication jobApplicationDetails) {
        Optional<JobApplication> jobApplicationOptional = jobApplicationService.findById(id);

        if (jobApplicationOptional.isPresent()) {
            JobApplication jobApplication = jobApplicationOptional.get();

            // Update the fields
            jobApplication.setCompany(jobApplicationDetails.getCompany());
            jobApplication.setPosition(jobApplicationDetails.getPosition());
            jobApplication.setStatus(jobApplicationDetails.getStatus());

            jobApplicationService.saveJobApplication(jobApplication);  // Save updated job application
            return ResponseEntity.ok("Job application updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job application not found.");
        }
    }
}
