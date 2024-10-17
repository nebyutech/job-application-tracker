package com.nebyu.jobapplicationtracker.controller;

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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobApplication>> getAllApplicationsForUser(@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            List<JobApplication> applications = jobApplicationService.getAllJobApplicationsForUser(user.get());
            return ResponseEntity.ok(applications);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobApplication(@PathVariable Long id) {
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.ok("Job application deleted successfully.");
    }
}
