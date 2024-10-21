package com.nebyu.jobapplicationtracker.config;//package com.nebyu.jobapplicationtracker.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private Environment environment; // Autowiring environment properties (optional for future use)
//
//    /**
//     * Bean for password encoding using BCrypt.
//     * This is a standard practice for securing user passwords.
//     */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * Security filter chain configuration. Disables CSRF protection and permits all requests
//     * (for development/testing purposes).
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // CSRF protection disabled for testing
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()  // Allow all requests without authentication for testing
//                );
//        return http.build();
//    }
//}



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
                        .anyRequest().permitAll()  // Allow all requests for now
                );
        return http.build();
    }
}
