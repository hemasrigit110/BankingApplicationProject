package com.example.account.controller;

import com.example.account.model.Account;
import com.example.account.service.AccountService;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


//    @PostMapping("/create")
//    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
//        Account created = accountService.createAccount(account);
//        return ResponseEntity.ok(created);
//    }

//    @GetMapping("/{accountNumber}/transactions")
//    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String accountNumber) {
//        List<Transaction> history = accountService.getTransactionHistory(accountNumber);
//        return ResponseEntity.ok(history);
//    }

    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable String accountNumber) {
        Account account = accountService.getAccountDetails(accountNumber);
        return ResponseEntity.ok(account);
    }

//    @GetMapping("/dashboard")
//    public ResponseEntity<Account> getAccountDashboard(@RequestHeader("Authorization") String token) {
//        return ResponseEntity.ok(accountService.getAccountDetails(token));
//}


//    @GetMapping("/{accountNumber}/balance")
//    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable String accountNumber) {
//        BigDecimal balance = accountService.getAccountBalance(accountNumber);
//        return ResponseEntity.ok(balance);
//    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Account>> getAllAccounts() {
//        List<Account> accounts = accountService.getAllAccounts();
//        return ResponseEntity.ok(accounts);
//    }
}


