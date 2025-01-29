package com.helpinghands.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.helpinghands.dto.UserLoginRequest;
import com.helpinghands.model.User;
import com.helpinghands.service.UserService;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    
//    @GetMapping("/login")
//    public String redirectToLogin() {
//        return "redirect:/login.html";
//    }
    
    @GetMapping("/test")
    public String test() {
        return "Controller is working!";
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Check if user already exists
    	
    	System.out.println("-------------------------------------------------");
    	
    	System.out.println("user is " + user);
    	
    	
        if (userService.checkIfUserExists(user.getUsername())) {
            return ResponseEntity.status(409).body(Collections.singletonMap("error", "Username already exists"));
        }
        // Register the user
        userService.registerUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));
    }

    // Login a user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        // Authenticate user
    	System.out.println("request is " + request);
    	System.out.println(request.getUsername());
    	System.out.println(request.getPassword());
        boolean isAuthenticated = userService.authenticateUser(request.getUsername(), request.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        }
        return ResponseEntity.status(401).body(Collections.singletonMap("error", "Invalid username or password"));
    }
}
