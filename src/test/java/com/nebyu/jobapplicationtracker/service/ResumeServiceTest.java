//package com.nebyu.jobapplicationtracker.service;
//
//import com.nebyu.jobapplicationtracker.model.Resume;
//import com.nebyu.jobapplicationtracker.repository.ResumeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ResumeServiceTest {
//
//    @Mock
//    private ResumeRepository resumeRepository;
//
//    @InjectMocks
//    private ResumeService resumeService;
//
//    private Resume testResume;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        testResume = new Resume();
//        testResume.setFileName("resume.pdf");
//        testResume.setFilePath("/path/to/resume.pdf");
//        testResume.setUploadDate(LocalDateTime.now());
//        testResume.setUserId(1L);
//        testResume.setId(1L);
//    }
//
//    @Test
//    void saveResume_shouldSaveResume() {
//        when(resumeRepository.save(any(Resume.class))).thenReturn(testResume);
//
//        Resume savedResume = resumeService.save(testResume);
//
//        assertNotNull(savedResume);
//        assertEquals("resume.pdf", savedResume.getFileName());
//        verify(resumeRepository, times(1)).save(testResume);
//    }
//}


package com.nebyu.jobapplicationtracker.service;

import com.nebyu.jobapplicationtracker.model.Resume;
import com.nebyu.jobapplicationtracker.repository.ResumeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ResumeServiceTest {

    @Mock
    private ResumeRepository resumeRepository;

    @InjectMocks
    private ResumeService resumeService;

    private Resume testResume;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testResume = new Resume();
        testResume.setId(1L);
        testResume.setFileName("resume.pdf");
        testResume.setFilePath("/path/to/resume.pdf");
        testResume.setUploadDate(LocalDateTime.now());
        testResume.setUserId(1L);
    }

    @Test
    void saveResume_ShouldSaveSuccessfully() {
        when(resumeRepository.save(any(Resume.class))).thenReturn(testResume);

        Resume savedResume = resumeService.save(testResume);

        assertNotNull(savedResume);
        assertEquals("resume.pdf", savedResume.getFileName());
        verify(resumeRepository, times(1)).save(testResume);
    }
}
