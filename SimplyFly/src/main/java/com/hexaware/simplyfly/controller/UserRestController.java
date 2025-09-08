package com.hexaware.simplyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.config.UserInfoServiceImpl;
import com.hexaware.simplyfly.dto.AuthRequest;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.service.JwtService;

@CrossOrigin(origins = "http://localhost:5173")
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
                // ✅ Load user to extract role
                User user = userServiceImpl.getUserByEmail(authRequest.getEmail());
                UserDetails userDetails = userServiceImpl.loadUserByUsername(authRequest.getEmail());

                String role = "USER";
                if (user.getRole() != null) {
                    role = user.getRole().name(); // enum or string in your entity
                }

                // ✅ generate token with role
                String token = jwtService.generateToken(userDetails, role);
                return ResponseEntity.ok(token);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.status(401).body("Authentication failed");
    }
}
