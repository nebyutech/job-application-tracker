package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Use @DataJpaTest for repository testing
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
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

    @Test
    void testFindByUsername() {
        // Create the first user
        User user = new User("testuser1", "testuser1@example.com", "password");
        userRepository.save(user);

        // Proceed with finding the first user
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByUsername("testuser1"));
        assertTrue(foundUser.isPresent(), "User should be found");
        assertEquals("testuser1", foundUser.get().getUsername());
    }
}
