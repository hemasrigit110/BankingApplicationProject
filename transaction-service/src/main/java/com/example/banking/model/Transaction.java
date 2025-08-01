package com.example.banking.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String AccountNumber;
    private String toAccount;
    private String fromAccount;
    private BigDecimal amount;
    private String description;
    private String status;
    private String TransactionType;
    private LocalDateTime transactionDate;

    public Transaction(Long id, String accountNumber, String toAccount, String fromAccount, BigDecimal amount, String description, String status, String transactionType, LocalDateTime transactionDate) {
        this.id = id;
        AccountNumber = accountNumber;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.description = description;
        this.status = status;
        TransactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public Transaction() {
        //default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionType() {
        return TransactionType;
    }

  public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }


    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
