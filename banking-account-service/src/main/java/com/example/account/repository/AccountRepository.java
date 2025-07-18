package com.example.account.repository;

import com.example.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);

    @Query("SELECT a.balance FROM Account a WHERE a.accountNumber = :accountNumber")
    BigDecimal findBalanceByAccountNumber(@Param("accountNumber") String accountNumber);



}