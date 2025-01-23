package com.helpinghands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Define BCryptPasswordEncoder bean to be injected into UserService
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            // Allow access to index and its sections
            .requestMatchers("/index.html", "/index.html#home", "/index.html#about", "/index.html#donate", "/index.html#contact").permitAll()
            // Allow static resources like CSS, JS, and images
            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
            // Protect pages requiring authentication
            .requestMatchers("/donate.html", "/api/donations/donate", "about","contact" ).permitAll()
            .anyRequest().authenticated()  // Any other request must be authenticated
            .and()
            .formLogin()
            .loginPage("/login.html")  // Point to the static login page
            .permitAll()
            .defaultSuccessUrl("/index.html#home", true)  // Redirect to home section after successful login
            .failureUrl("/login.html?error=true")  // Show error on login failure
            .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/login.html?logout=true");  // Redirect to login page after logout

        return http.build();
    }
}
