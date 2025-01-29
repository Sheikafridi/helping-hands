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
            .authorizeHttpRequests(auth -> auth
                // Allow access to login, register, and test endpoints
                .requestMatchers("/api/register", "/api/login", "/api/test").permitAll()
                // Allow access to static resources
                .requestMatchers("/index.html", "/index.html#home", "/index.html#about", "/index.html#donate", "/index.html#contact").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/donate.html", "/api/donations/donate", "/about", "/contact").permitAll()
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.html")  // Use custom login page
                .permitAll()
                .defaultSuccessUrl("/index.html#home", true)
                .failureUrl("/login.html?error=true")
            )
            .logout(logout -> logout
                .permitAll()
                .logoutSuccessUrl("/login.html?logout=true")
            );

        return http.build();
    }
    	
 


}
