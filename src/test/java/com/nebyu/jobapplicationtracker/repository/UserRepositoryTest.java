package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test") // Add this to use application-test.properties


public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        // Set up test data
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setEmail("testuser@example.com");
        userRepository.save(user);
    }

//    @Test
//    public void testFindByUsername() {
//        Optional<User> user = userRepository.findByUsername("testuser");
//        assertTrue(user.isPresent());


    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("testuser");
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
    }
}