package com.nebyu.jobapplicationtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.service.JobApplicationService;
import com.nebyu.jobapplicationtracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class JobApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobApplicationService jobApplicationService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;
    private JobApplication testJobApplication;

    @BeforeEach
    public void setup() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");

        testJobApplication = new JobApplication();
        testJobApplication.setId(1L);
        testJobApplication.setCompany("Test Company");
        testJobApplication.setPosition("Developer");
        testJobApplication.setStatus("Applied");
        testJobApplication.setUser(testUser);
        testJobApplication.setApplicationDate(LocalDate.now());
    }

    @Test
    public void createJobApplication_Success() throws Exception {
        Mockito.when(userService.findById(1L)).thenReturn(Optional.of(testUser));
        Mockito.when(jobApplicationService.saveJobApplication(Mockito.any(JobApplication.class)))
                .thenReturn(testJobApplication);

        mockMvc.perform(post("/api/applications/create")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testJobApplication)))
                .andExpect(status().isOk())
                // Expect a JSON response with the message and applicationId
                .andExpect(jsonPath("$.message").value("Job application created successfully."))
                .andExpect(jsonPath("$.applicationId").value(1));  // Verify applicationId is included
    }


    @Test
    public void getAllApplicationsForUser_Success() throws Exception {
        Mockito.when(userService.findById(1L)).thenReturn(Optional.of(testUser));
        Mockito.when(jobApplicationService.getAllJobApplicationsForUser(testUser))
                .thenReturn(Arrays.asList(testJobApplication));

        mockMvc.perform(get("/api/applications/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].company").value("Test Company"))
                .andExpect(jsonPath("$[0].position").value("Developer"));
    }

    @Test
    public void deleteJobApplication_Success() throws Exception {
        Mockito.doNothing().when(jobApplicationService).deleteJobApplication(1L);

        mockMvc.perform(delete("/api/applications/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Job application deleted successfully."));
    }
}