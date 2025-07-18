//package com.example.account.service;
//import com.example.account.exception.ResourceNotFoundException;
//import com.example.account.model.Account;
//import com.example.account.repository.AccountRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.Optional;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AccountServiceImplTest {
//
//    private AccountRepository accountRepository;
//    private AccountServiceImpl accountService;
//
//    @BeforeEach
//    void setUp() {
//        accountRepository = Mockito.mock(AccountRepository.class);
//        accountService = new AccountServiceImpl();
//        // Use reflection to inject the mock if field is private
//        java.lang.reflect.Field field = null;
//        try {
//            field = AccountServiceImpl.class.getDeclaredField("accountRepository");
//            field.setAccessible(true);
//            field.set(accountService, accountRepository);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void getAccountDetails_returnsAccount_whenAccountExists() {
//        Account account = new Account();
//        account.setAccountNumber("1234567890");
//        Mockito.when(accountRepository.findByAccountNumber("1234567890"))
//                .thenReturn(Optional.of(account));
//
//        Account result = accountService.getAccountDetails("1234567890");
//
//        assertEquals("1234567890", result.getAccountNumber());
//    }
//
//    @Test
//    void getAccountDetails_throwsException_whenAccountDoesNotExist() {
//        Mockito.when(accountRepository.findByAccountNumber("notfound"))
//                .thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> accountService.getAccountDetails("notfound"));
//    }
//
//    @Test
//    void getAccountBalance_returnsBalance_whenAccountExists() {
//        Mockito.when(accountRepository.findBalanceByAccountNumber("1234567890"))
//                .thenReturn(new BigDecimal("1000.00"));
//
//        BigDecimal balance = accountService.getAccountBalance("1234567890");
//
//        assertEquals(new BigDecimal("1000.00"), balance);
//    }
//
//    @Test
//    void updateAccountBalance_updatesBalance_whenAccountExists() {
//        Account account = new Account();
//        account.setAccountNumber("1234567890");
//        account.setBalance(new BigDecimal("500.00"));
//
//        Mockito.when(accountRepository.findByAccountNumber("1234567890"))
//                .thenReturn(Optional.of(account));
//        Mockito.when(accountRepository.save(account)).thenReturn(account);
//
//        Account updated = accountService.updateAccountBalance("1234567890", new BigDecimal("1500.00"));
//
//        assertEquals(new BigDecimal("1500.00"), updated.getBalance());
//    }
//
//    @Test
//    void getAllAccounts_returnsEmptyList_whenNoAccountsExist() {
//        Mockito.when(accountRepository.findAll()).thenReturn(Collections.emptyList());
//
//        List<Account> accounts = accountService.getAllAccounts();
//
//        assertTrue(accounts.isEmpty());
//    }
//
//    @Test
//    void createAccount_savesAndReturnsAccount() {
//        Account account = new Account();
//        account.setAccountNumber("1234567890");
//        account.setBalance(new BigDecimal("1000.00"));
//
//        Mockito.when(accountRepository.save(account)).thenReturn(account);
//
//        Account created = accountService.createAccount(account);
//
//        assertEquals("1234567890", created.getAccountNumber());
//        assertEquals(new BigDecimal("1000.00"), created.getBalance());
//    }
//
//    @Test
//    void getTransactionHistory_returnsEmptyList() {
//        List<?> transactions = accountService.getTransactionHistory("any");
//        assertTrue(transactions.isEmpty());
//    }
//}