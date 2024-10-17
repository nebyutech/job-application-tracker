package com.nebyu.jobapplicationtracker.controller;

import com.nebyu.jobapplicationtracker.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testResourceNotFoundException() throws Exception {
        mockMvc.perform(get("/api/applications/999"))  // Non-existent endpoint
                .andExpect(status().isNotFound())
                .andExpect(content().string("Resource not found"));
    }

    @Test
    public void testGlobalException() throws Exception {
        mockMvc.perform(get("/api/invalidEndpoint"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An error occurred: No handler found"));
    }
}
