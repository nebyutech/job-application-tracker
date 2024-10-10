package com.nebyu.jobapplicationtracker.controller;

import com.nebyu.jobapplicationtracker.model.Resume;
import com.nebyu.jobapplicationtracker.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("File cannot be null or empty");
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/path/to/save/" + fileName;

        Resume resume = new Resume();
        resume.setFileName(fileName);
        resume.setFilePath(filePath);
        resume.setUploadDate(LocalDateTime.now());
        resume.setUserId(userId);

        resumeService.save(resume);
        return ResponseEntity.ok("Resume uploaded successfully");
    }
}


