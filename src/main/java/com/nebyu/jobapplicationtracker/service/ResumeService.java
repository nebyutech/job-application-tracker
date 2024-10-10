package com.nebyu.jobapplicationtracker.service;

import com.nebyu.jobapplicationtracker.model.Resume;
import com.nebyu.jobapplicationtracker.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    public void save(Resume resume) {
        resumeRepository.save(resume); // Save to database
    }
}
