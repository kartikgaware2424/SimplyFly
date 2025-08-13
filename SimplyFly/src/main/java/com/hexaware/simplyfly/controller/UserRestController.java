package com.hexaware.simplyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.config.UserInfoServiceImpl;
import com.hexaware.simplyfly.dto.AuthRequest;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class UserRestController {

    @Autowired
    private UserInfoServiceImpl userServiceImpl;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userServiceImpl.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            if (auth.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getEmail());
                return ResponseEntity.ok(token);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.status(401).body("Authentication failed");
    }
   
}