package com.nebyu.jobapplicationtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for testing purposes
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Disable security for testing
                );
        return http.build();
    }
}




//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Disable CSRF for API testing via Postman
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()  // Public access for register and login
//                        .anyRequest().authenticated()  // All other requests require authentication
//                )
//                .httpBasic();  // Basic authentication without withDefaults()
//        return http.build();
//    }
//}
