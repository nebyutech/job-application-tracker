package com.nebyu.jobapplicationtracker.controller;

import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;

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
        // Encode the password using BCryptPasswordEncoder
        testUser.setPassword(new BCryptPasswordEncoder().encode("password123"));
        testUser.setEmail("testuser@example.com");
    }

    @Test
    public void registerUser_Success() throws Exception {
        // Mock the service call
        Mockito.when(userService.findByUsername(testUser.getUsername())).thenReturn(null);
        Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully."))
                .andExpect(jsonPath("$.userId").isNotEmpty());  // Check that userId is returned
    }



    @Test
    public void registerUser_UsernameTaken() throws Exception {
        // Simulate that the username is already taken
        Mockito.when(userService.findByUsername(testUser.getUsername())).thenReturn(testUser);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Username already taken."));
    }



    @Test
    public void loginUser_Success() throws Exception {
        // Mock the service call for login
        Mockito.when(userService.findByUsername(testUser.getUsername())).thenReturn(testUser);
        // Ensure password matches correctly
        Mockito.when(passwordEncoder.matches("password123", testUser.getPassword())).thenReturn(true);

        // Prepare the request with raw password (not encoded)
        User loginUser = new User();
        loginUser.setUsername("testuser");
        loginUser.setPassword("password123");  // Raw password

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUser)))  // Use raw password in the request body
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Login successful."));
    }


    @Test
    public void loginUser_InvalidCredentials() throws Exception {
        // Mock the service call for invalid credentials
        Mockito.when(userService.findByUsername(testUser.getUsername())).thenReturn(testUser);
        Mockito.when(passwordEncoder.matches("password123", testUser.getPassword())).thenReturn(false);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isBadRequest())
                .andExpect((ResultMatcher) content().string("Invalid username or password."));
    }
}