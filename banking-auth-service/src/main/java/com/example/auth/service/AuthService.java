package com.example.auth.service;

import com.example.auth.dto.AuthResponse;
import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignUpRequest;
import com.example.auth.exception.InvalidCredentialsException;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.model.Account;
import com.example.auth.model.User;
import com.example.auth.repository.AccountRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.exception.BadRequestException;
import com.example.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    //  Login Function
    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername())
                //.orElseThrow(() -> new UsernameNotFoundException("User not found"));
                .orElseThrow(() -> new ResourceNotFoundException("User","username",req.getUsername()));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token, "Login successful");
    }
    //  Register Function
    public String register (SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }

        User user = request.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        Account account = request.toAccount();
        account.setUser(savedUser);
        accountRepository.save(account);
        return "user registered successfully";
    }

}
