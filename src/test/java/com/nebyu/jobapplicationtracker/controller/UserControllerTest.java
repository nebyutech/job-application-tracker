//package com.nebyu.jobapplicationtracker.jobapplicationtracker.controller;
//
//import com.nebyu.jobapplicationtracker.jobapplicationtracker.model.User;
//import com.nebyu.jobapplicationtracker.jobapplicationtracker.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void testRegisterUser() throws Exception {
//        User user = new User();
//        user.setUsername("testuser");
//        user.setPassword("password");
//
//        when(userService.findByUsername("testuser")).thenReturn(Optional.empty());
//        when(userService.registerUser(user)).thenReturn(user);
//
//        mockMvc.perform(post("/api/users/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"username\": \"testuser\", \"password\": \"password\", \"email\": \"test@example.com\"}"))
//                .andExpect(status().isOk());
//    }
//}
