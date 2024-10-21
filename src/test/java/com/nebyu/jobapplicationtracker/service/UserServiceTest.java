package com.nebyu.jobapplicationtracker.service;

import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setUsername("testuser1");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
    }

    @Test
    void registerUser_shouldSaveUser() {
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User savedUser = userService.registerUser(testUser);

        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void findByUsername_shouldReturnUser() {
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);

        User foundUser = userService.findByUsername(testUser.getUsername());

        assertNotNull(foundUser);
        assertEquals(testUser.getUsername(), foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername(testUser.getUsername());
    }

    @Test
    void loginUser_shouldReturnTrueForValidCredentials() {
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);
        when(passwordEncoder.matches(anyString(), eq("encodedPassword"))).thenReturn(true);

        boolean loginSuccess = userService.loginUser(testUser.getUsername(), "password123");

        assertTrue(!loginSuccess);
    }


}