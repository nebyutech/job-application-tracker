//package com.nebyu.jobapplicationtracker.controller;
//
//import com.nebyu.jobapplicationtracker.model.JobApplication;
//import com.nebyu.jobapplicationtracker.model.User;
//import com.nebyu.jobapplicationtracker.service.JobApplicationService;
//import com.nebyu.jobapplicationtracker.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/applications")
//public class JobApplicationController {
//
//    @Autowired
//    private JobApplicationService jobApplicationService; // Service layer for job application logic
//
//    @Autowired
//    private UserService userService; // Service layer for user logic
//
//    /**
//     * Create a new job application associated with a specific user.
//     * Returns the application ID and a success message in the response.
//     */
//    @PostMapping("/create")
//    public ResponseEntity<Map<String, Object>> createJobApplication(@RequestParam Long userId, @RequestBody JobApplication jobApplication) {
//        Optional<User> user = userService.findById(userId);
//
//        if (user.isPresent()) {
//            jobApplication.setUser(user.get());
//            JobApplication savedJobApplication = jobApplicationService.saveJobApplication(jobApplication);  // Save the job application
//
//            // Prepare the response with the application ID
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Job application created successfully.");
//            response.put("applicationId", savedJobApplication.getId());
//
//            return ResponseEntity.ok(response);  // Return a 200 OK response
//        } else {
//            return ResponseEntity.badRequest().body(Map.of("message", "User not found."));
//        }
//    }
//
//    /**
//     * Retrieve all job applications associated with a specific user.
//     */
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<JobApplication>> getAllApplicationsForUser(@PathVariable Long userId) {
//        Optional<User> user = userService.findById(userId);
//        if (user.isPresent()) {
//            List<JobApplication> applications = jobApplicationService.getAllJobApplicationsForUser(user.get());
//            return ResponseEntity.ok(applications);
//        }
//        return ResponseEntity.badRequest().build();  // Return 400 Bad Request if user not found
//    }
//
//    /**
//     * Delete a specific job application by its ID.
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteJobApplication(@PathVariable Long id) {
//        jobApplicationService.deleteJobApplication(id);
//        return ResponseEntity.ok("Job application deleted successfully.");
//    }
//
//    /**
//     * Update an existing job application by its ID.
//     */
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateJobApplication(@PathVariable Long id, @RequestBody JobApplication jobApplicationDetails) {
//        Optional<JobApplication> jobApplicationOptional = jobApplicationService.findById(id);
//
//        if (jobApplicationOptional.isPresent()) {
//            JobApplication jobApplication = jobApplicationOptional.get();
//            jobApplication.setCompany(jobApplicationDetails.getCompany());  // Update fields
//            jobApplication.setPosition(jobApplicationDetails.getPosition());
//            jobApplication.setStatus(jobApplicationDetails.getStatus());
//            jobApplicationService.saveJobApplication(jobApplication);  // Save updated application
//            return ResponseEntity.ok("Job application updated successfully.");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job application not found.");
//        }
//    }
//}



package com.nebyu.jobapplicationtracker.controller;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.service.JobApplicationService;
import com.nebyu.jobapplicationtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private UserService userService;

    // Create a new job application for a specific user
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createJobApplication(@RequestParam Long userId, @RequestBody JobApplication jobApplication) {
        // Manual validation: Ensure userId and jobApplication fields are not null or empty
        if (userId == null || jobApplication.getCompany() == null || jobApplication.getPosition() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Missing required fields."));
        }

        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            jobApplication.setUser(user.get());
            JobApplication savedJobApplication = jobApplicationService.saveJobApplication(jobApplication);

            // Build the response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Job application created successfully.");
            response.put("applicationId", savedJobApplication.getId());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "User not found."));
        }
    }

    // Retrieve all job applications for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getAllApplicationsForUser(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "User ID cannot be null."));
        }

        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            List<JobApplication> applications = jobApplicationService.getAllJobApplicationsForUser(user.get());
            Map<String, Object> response = new HashMap<>();
            response.put("applications", applications);
            response.put("totalApplications", applications.size());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(Map.of("message", "User not found."));
    }

    // Update a specific job application
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateJobApplication(@PathVariable Long id, @RequestBody JobApplication jobApplicationDetails) {
        if (id == null || jobApplicationDetails.getCompany() == null || jobApplicationDetails.getPosition() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Missing required fields."));
        }

        Optional<JobApplication> jobApplicationOptional = jobApplicationService.findById(id);
        if (jobApplicationOptional.isPresent()) {
            JobApplication jobApplication = jobApplicationOptional.get();
            jobApplication.setCompany(jobApplicationDetails.getCompany());
            jobApplication.setPosition(jobApplicationDetails.getPosition());
            jobApplication.setStatus(jobApplicationDetails.getStatus());

            jobApplicationService.saveJobApplication(jobApplication);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Job application updated successfully.");
            response.put("applicationId", jobApplication.getId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Job application not found."));
        }
    }

    // Delete a specific job application by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobApplication(@PathVariable Long id) {
        // Ensure this API is properly configured
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.ok("Job application deleted successfully.");
    }
    }

