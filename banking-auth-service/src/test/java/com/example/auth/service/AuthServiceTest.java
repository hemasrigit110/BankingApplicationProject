package com.example.auth.service;

import com.example.auth.model.User;
import com.example.auth.model.Account;
import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignUpRequest;
import com.example.auth.dto.AuthResponse;
import com.example.auth.repository.UserRepository;
import com.example.auth.repository.AccountRepository;
import com.example.auth.exception.InvalidCredentialsException;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.exception.BadRequestException;
import com.example.auth.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_returnsAuthResponse_whenCredentialsAreValid() {
        User user = new User();
        user.setUsername("validUser");
        user.setPassword("encodedPassword");

        Mockito.when(userRepository.findByUsername("validUser"))
                .thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches("validPassword", "encodedPassword"))
                .thenReturn(true);
        Mockito.when(jwtUtil.generateToken("validUser"))
                .thenReturn("validToken");

        AuthResponse response = authService.login(new LoginRequest("validUser", "validPassword"));

        assertEquals("validToken", response.getToken());
        assertEquals("Login successful", response.getMessage());
    }

    @Test
    void login_throwsInvalidCredentialsException_whenPasswordIsIncorrect() {
        User user = new User();
        user.setUsername("validUser");
        user.setPassword("encodedPassword");

        Mockito.when(userRepository.findByUsername("validUser"))
                .thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches("invalidPassword", "encodedPassword"))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class,
            () -> authService.login(new LoginRequest("validUser", "invalidPassword")));
    }

    @Test
    void login_throwsResourceNotFoundException_whenUserDoesNotExist() {
        Mockito.when(userRepository.findByUsername("nonexistentUser"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
            () -> authService.login(new LoginRequest("nonexistentUser", "anyPassword")));
    }

    @Test
    void register_returnsSuccessMessage_whenUserIsRegisteredSuccessfully() {
        SignUpRequest request = new SignUpRequest();
        request.setUsername("newUser");
        request.setPassword("newPassword");

        User user = new User();
        user.setUsername("newUser");
        user.setPassword("encodedPassword");

        Account account = new Account();

        Mockito.when(userRepository.existsByUsername("newUser"))
                .thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(user);
        Mockito.when(accountRepository.save(Mockito.any(Account.class)))
                .thenReturn(account);
        Mockito.when(passwordEncoder.encode("newPassword"))
                .thenReturn("encodedPassword");

        String result = authService.register(request);

        assertEquals("user registered successfully", result);
    }

    @Test
    void register_throwsBadRequestException_whenUsernameIsAlreadyTaken() {
        Mockito.when(userRepository.existsByUsername("existingUser"))
                .thenReturn(true);

        SignUpRequest request = new SignUpRequest();
        request.setUsername("existingUser");

        assertThrows(BadRequestException.class,
            () -> authService.register(request));
    }
}