package com.example.account.service;

import com.example.account.model.Account;
import jakarta.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account getAccountDetails(String accountNumber);

    BigDecimal getAccountBalance(String accountNumber);

    Account updateAccountBalance(String accountNumber, BigDecimal newBalance);

    List<Account> getAllAccounts();

    Account createAccount(Account account);

    List<Transaction> getTransactionHistory(String accountNumber);
}
