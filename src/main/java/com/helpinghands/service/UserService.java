package com.helpinghands.service;

import com.helpinghands.model.User;
import com.helpinghands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Check if a user exists
    public boolean checkIfUserExists(String username) {
        return userRepository.existsByUsername(username);
    }
    
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Register a new user
    public void registerUser(String username, String password) {
    	if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be empty");
        }
    	
    	User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt the password
        userRepository.save(user);
    }

    // Authenticate user
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println("user is " + user);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
