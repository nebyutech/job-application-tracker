package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setup() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setEmail("testuser@example.com");
    }

    @Test
    public void testFindByUsername() {
        userRepository.save(testUser);

        User foundUser = userRepository.findByUsername("testuser");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    public void testFindByEmail() {
        userRepository.save(testUser);

        Optional<User> foundUser = userRepository.findByEmail("testuser@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("testuser@example.com", foundUser.get().getEmail());
    }
}
