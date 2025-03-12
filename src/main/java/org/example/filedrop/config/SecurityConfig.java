package org.example.filedrop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Configuring HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/register").permitAll() // Allow login and register pages
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll() // Allow logout
                );
        return http.build(); // Musimy zwrócić zbudowany obiekt SecurityFilterChain
    }

    // BCryptPasswordEncoder bean to encode passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }
}
