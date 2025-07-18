// banking-account-service/src/test/java/com/example/account/model/AccountTest.java

package com.example.account.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testConstructorAndGetters() {
        Account account = new Account(
                1L,
                "John Doe",
                "johndoe",
                "1234567890",
                "IFSC1234",
                "SAVINGS",
                new BigDecimal("1000.00")
        );

        assertEquals(1L, account.getId());
        assertEquals("John Doe", account.getAccountHolderName());
        assertEquals("johndoe", account.getUsername());
        assertEquals("1234567890", account.getAccountNumber());
        assertEquals("IFSC1234", account.getIfscCode());
        assertEquals("SAVINGS", account.getAccountType());
        assertEquals(new BigDecimal("1000.00"), account.getBalance());
    }

    @Test
    void testSetters() {
        Account account = new Account();
        account.setId(2L);
        account.setAccountHolderName("Jane Doe");
        account.setUsername("janedoe");
        account.setAccountNumber("0987654321");
        account.setIfscCode("IFSC5678");
        account.setAccountType("CURRENT");
        account.setBalance(new BigDecimal("500.00"));

        assertEquals(2L, account.getId());
        assertEquals("Jane Doe", account.getAccountHolderName());
        assertEquals("janedoe", account.getUsername());
        assertEquals("0987654321", account.getAccountNumber());
        assertEquals("IFSC5678", account.getIfscCode());
        assertEquals("CURRENT", account.getAccountType());
        assertEquals(new BigDecimal("500.00"), account.getBalance());
    }
}