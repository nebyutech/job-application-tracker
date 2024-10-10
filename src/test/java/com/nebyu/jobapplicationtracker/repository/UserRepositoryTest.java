package com.nebyu.jobapplicationtracker.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import com.nebyu.jobapplicationtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    void testFindByUsername() {
        // Create the first user
        User user = new User("testuser1", "testuser1@example.com", "password");
        userRepository.save(user);

        // Retrieve user by username
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByUsername("testuser1"));

        // Check if user exists
        assertTrue(foundUser.isPresent(), "User should be found in the repository");

        // Now get the actual user object from the Optional
        User retrievedUser = foundUser.get();

        // Perform assertion on retrieved user
        assertEquals("testuser1", retrievedUser.getUsername(), "Usernames should match");
        assertEquals("testuser1@example.com", retrievedUser.getEmail(), "Emails should match");
        assertEquals("password", retrievedUser.getPassword(), "Passwords should match");
    }
}