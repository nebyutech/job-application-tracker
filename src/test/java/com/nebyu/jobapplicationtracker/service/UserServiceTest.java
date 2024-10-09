//package com.nebyu.jobapplicationtracker.jobapplicationtracker.service;
//
//import com.nebyu.jobapplicationtracker.jobapplicationtracker.model.User;
//import com.nebyu.jobapplicationtracker.jobapplicationtracker.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//public class UserServiceTest {
//
//    @InjectMocks
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testRegisterUser() {
//        User user = new User();
//        user.setUsername("testuser");
//        user.setPassword("password");
//        user.setEmail("test@example.com");
//
//        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
//        when(userRepository.save(user)).thenReturn(user);
//
//        User registeredUser = userService.registerUser(user);
//
//        assertEquals("encodedPassword", registeredUser.getPassword());
//        assertEquals("testuser", registeredUser.getUsername());
//    }
//
//
//
//}
