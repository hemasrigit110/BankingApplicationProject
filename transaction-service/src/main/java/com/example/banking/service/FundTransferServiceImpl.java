package com.example.banking.service;

import com.example.banking.dto.FundTransferRequest;
import com.example.banking.dto.FundTransferResponse;
import com.example.banking.exception.AccountNotFoundException;
import com.example.banking.model.Account;

import com.example.banking.model.Transaction;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public FundTransferServiceImpl(AccountRepository accountRepository,
                                   TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }



    @Transactional
    @Override
    public FundTransferResponse transferFunds(FundTransferRequest request) {
        // Validate request
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Get destination account
        Account destinationAccount = accountRepository
                .findByAccountNumberAndIfscCode(request.getToAccount(), request.getIfscCode())
                .orElseThrow(() -> new AccountNotFoundException("Destination account not found"));

        // Create transaction record (simplified - no balance updates)
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(destinationAccount.getAccountNumber());
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionType("CREDIT");

        transactionRepository.save(transaction);

        return buildResponse(transaction);
    }

    private FundTransferResponse buildResponse(Transaction transaction) {
        FundTransferResponse response = new FundTransferResponse();
        response.setTransactionId(transaction.getId());
        response.setStatus("SUCCESS");
        response.setMessage("Fund transfer initiated");
        response.setTransactionDate(transaction.getTransactionDate());
        return response;
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactionRepository.findByFromAccountOrToAccount(accountNumber, accountNumber);
    }

}