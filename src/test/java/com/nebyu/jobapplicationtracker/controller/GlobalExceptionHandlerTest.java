package com.nebyu.jobapplicationtracker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
        mockMvc.perform(get("/api/invalidEndpoint"))
                .andExpect(status().isNotFound())  // Expect 404 Not Found
                .andExpect(content().string("An error occurred: No handler found."));
    }



    @Test
    public void testMethodNotSupportedException() throws Exception {
        mockMvc.perform(get("/api/applications/999"))  // Assuming GET is not allowed on this endpoint
                .andExpect(status().isMethodNotAllowed())  // Expecting 405
                .andExpect(content().string("An error occurred: Request method 'GET' is not supported."));
    }

}