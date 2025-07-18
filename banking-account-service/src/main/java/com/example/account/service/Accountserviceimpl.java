package com.example.account.service;

import com.example.account.model.Account;

import com.example.account.repository.AccountRepository;

import com.example.account.model.User;


import com.example.account.repository.UserDetailsRepository;

import jakarta.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class Accountserviceimpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserDetailsRepository userRepository;

//    @Autowired
//    private AuthClient authClient;


    @Override
    public Account getAccountDetails(String token) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", token);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                "http://localhost:8082/auth/validate", // Auth Service URL
//                HttpMethod.POST,
//                entity,
//                String.class
//        );
//        if (response.getStatusCode() != HttpStatus.OK) {
//            throw new RuntimeException("Unauthorized");
//        }
//
//        String username = response.getBody();
//
//        // 2. Get account by username
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        ResponseEntity<String> response = authClient.validateToken(token);
//
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new RuntimeException("Invalid token");
//        }
//
//        String username = response.getBody();
//        Optional<User> user = userRepository.findByUsername(username);
//
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        //return user.get().getAccount();
        return null;
   }

    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        return null;
    }

    @Override
    public Account updateAccountBalance(String accountNumber, BigDecimal newBalance) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return List.of();
    }

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountNumber) {
        return List.of();
    }
}








