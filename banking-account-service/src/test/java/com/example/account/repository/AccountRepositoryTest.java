package com.example.account.repository;

import com.example.account.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findByAccountNumber_returnsAccount_whenAccountExists() {
        Account account = new Account();
        account.setAccountNumber("1234567890");
        account.setBalance(new BigDecimal("1000.00"));
        accountRepository.save(account);

        Optional<Account> result = accountRepository.findByAccountNumber("1234567890");

        assertTrue(result.isPresent());
        assertEquals("1234567890", result.get().getAccountNumber());
    }

    @Test
    void findByAccountNumber_returnsEmpty_whenAccountDoesNotExist() {
        Optional<Account> result = accountRepository.findByAccountNumber("nonexistent");

        assertFalse(result.isPresent());
    }

    @Test
    void findBalanceByAccountNumber_returnsBalance_whenAccountExists() {
        Account account = new Account();
        account.setAccountNumber("1234567890");
        account.setBalance(new BigDecimal("1000.00"));
        accountRepository.save(account);

        BigDecimal balance = accountRepository.findBalanceByAccountNumber("1234567890");

        assertEquals(new BigDecimal("1000.00"), balance);
    }


}