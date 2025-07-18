package com.example.auth.controller;

import com.example.auth.dto.ApiResponse;
import com.example.auth.dto.JwtAuthenticationResponse;
import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignUpRequest;
import com.example.auth.exception.InvalidCredentialsException;
import com.example.auth.model.Account;
import com.example.auth.model.User;
import com.example.auth.security.JwtUtil;
import com.example.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
//        return ResponseEntity.ok(authService.login(request));
//    }



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        try {
            com.example.auth.dto.AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity
                    .status(401)
                    .body(new ApiResponse(false, "Authentication failed: " + e.getMessage()));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtUtil.extractUsername(token.substring(7));
            return ResponseEntity.ok(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
 }
}
}










//        User user = new User();
//        user.setUsername(signUpRequest.getUsername());
//        user.setPassword(signUpRequest.getPassword());
//        Account account = new Account();
//        account.setAccountHolderName(signUpRequest.getAccountHolderName());
//        account.setAccountNumber(signUpRequest.getAccountNumber());
//        account.setIfscCode(signUpRequest.getIfscCode());
//        account.setAccountType("SAVINGS");
////        account.setBalance(BigDecimal.ZERO);
//
//        Long userId = authService.registerUser(user, account);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/users/{id}")
//                .buildAndExpand(userId).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "User registered successfully"));
//    }


