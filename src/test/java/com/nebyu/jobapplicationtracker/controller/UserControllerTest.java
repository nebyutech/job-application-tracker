package com.nebyu.jobapplicationtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    public void setup() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword(new BCryptPasswordEncoder().encode("password123"));
        testUser.setEmail("testuser@example.com");
    }

    @Test
    public void registerUser_Success() throws Exception {
        // Manual validation inside the test
        if (testUser.getUsername() == null || testUser.getPassword() == null || testUser.getEmail() == null) {
            throw new IllegalArgumentException("Username, password, and email cannot be null");
        }

        Mockito.when(userService.findByUsername(testUser.getUsername())).thenReturn(null);
        Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.message").value("User registered successfully."))
                .andExpect((ResultMatcher) jsonPath("$.userId").value(testUser.getId()));
    }

    @Test
    public void loginUser_Success() throws Exception {
        Mockito.when(userService.findByUsername(testUser.getUsername())).thenReturn(testUser);
        Mockito.when(passwordEncoder.matches("password123", testUser.getPassword())).thenReturn(true);

        User loginUser = new User();
        loginUser.setUsername("testuser");
        loginUser.setPassword("password123");

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUser)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.message").value("Login successful."))
                .andExpect((ResultMatcher) jsonPath("$.userId").value(testUser.getId()));
    }

    @Test
    public void loginUser_InvalidCredentials() throws Exception {
        Mockito.when(userService.findByUsername(testUser.getUsername())).thenReturn(testUser);
        Mockito.when(passwordEncoder.matches("wrongPassword", testUser.getPassword())).thenReturn(false);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid username or password."));
    }
}
