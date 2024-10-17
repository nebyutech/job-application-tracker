package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Use the actual database
public class ResumeRepositoryTest {

    @Autowired
    private ResumeRepository resumeRepository;

    private Resume testResume;

    @BeforeEach
    public void setUp() {
        testResume = new Resume();
        testResume.setFileName("resume.pdf");
        testResume.setFilePath("/path/to/resume.pdf");
        testResume.setUploadDate(LocalDateTime.now());
        testResume.setUserId(1L);

        // Save the resume and retrieve the saved instance to get the generated ID
        testResume = resumeRepository.save(testResume);  // save and retrieve the saved resume with its generated ID
    }

    @Test
    public void testSaveResume() {
        // Retrieve the resume using the generated ID
        Optional<Resume> foundResume = resumeRepository.findById(testResume.getId());

        assertTrue(foundResume.isPresent());
        assertEquals("resume.pdf", foundResume.get().getFileName());
    }

    @Test
    public void testDeleteResume() {
        // Delete using the generated ID
        resumeRepository.deleteById(testResume.getId());

        Optional<Resume> foundResume = resumeRepository.findById(testResume.getId());
        assertFalse(foundResume.isPresent());
    }
}
