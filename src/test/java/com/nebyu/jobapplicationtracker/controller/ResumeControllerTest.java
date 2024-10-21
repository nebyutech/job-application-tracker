package com.nebyu.jobapplicationtracker.controller;

import com.nebyu.jobapplicationtracker.model.Resume;
import com.nebyu.jobapplicationtracker.service.ResumeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ResumeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResumeService resumeService;

    private Resume testResume;

    @BeforeEach
    public void setup() {
        testResume = new Resume();
        testResume.setId(1L);
        testResume.setFileName("resume.pdf");
        testResume.setFilePath("/path/to/resume.pdf");
        testResume.setUserId(1L);
    }

    @Test
    public void uploadResume_Success() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "resume.pdf", MediaType.APPLICATION_PDF_VALUE, "dummy content".getBytes()
        );

        Mockito.when(resumeService.save(Mockito.any(Resume.class))).thenReturn(testResume);

        mockMvc.perform(multipart("/api/resumes/upload")
                        .file(file)
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Resume uploaded successfully"));
    }

    @Test
    public void uploadResume_FileEmpty() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file", "", MediaType.APPLICATION_PDF_VALUE, "".getBytes()
        );

        mockMvc.perform(multipart("/api/resumes/upload")
                        .file(emptyFile)
                        .param("userId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("File cannot be null or empty"));
    }
}