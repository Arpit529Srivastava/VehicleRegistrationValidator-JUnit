package com.example.banking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionServiceTest {

    private TransactionService transactionService;
    private BankAccount account;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
        account = new BankAccount("ACC456", "Jane Smith", 500.0);
    }

    @AfterEach
    void tearDown() {
        transactionService = null;
        account = null;
    }

    @Test
    @DisplayName("Depositing a positive amount returns true and updates the account balance")
    void depositToAccount_withPositiveAmount_returnsTrueAndUpdatesBalance() {
        boolean result = transactionService.depositToAccount(account, 250.0);
        assertTrue(result);
        assertEquals(750.0, account.getBalance());
    }

    @Test
    @DisplayName("Depositing zero returns false and leaves the account balance unchanged")
    void depositToAccount_withZeroAmount_returnsFalse() {
        boolean result = transactionService.depositToAccount(account, 0.0);
        assertFalse(result);
        assertEquals(500.0, account.getBalance());
    }

    @Test
    @DisplayName("Depositing a negative amount returns false and leaves the account balance unchanged")
    void depositToAccount_withNegativeAmount_returnsFalse() {
        boolean result = transactionService.depositToAccount(account, -50.0);
        assertFalse(result);
        assertEquals(500.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdrawing a valid amount returns true and reduces the account balance")
    void withdrawFromAccount_withSufficientFunds_returnsTrueAndReducesBalance() {
        boolean result = transactionService.withdrawFromAccount(account, 200.0);
        assertTrue(result);
        assertEquals(300.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdrawing more than the balance returns false and leaves the balance unchanged")
    void withdrawFromAccount_withInsufficientFunds_returnsFalse() {
        boolean result = transactionService.withdrawFromAccount(account, 10000.0);
        assertFalse(result);
        assertEquals(500.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdrawing a negative amount returns false and leaves the balance unchanged")
    void withdrawFromAccount_withNegativeAmount_returnsFalse() {
        boolean result = transactionService.withdrawFromAccount(account, -20.0);
        assertFalse(result);
        assertEquals(500.0, account.getBalance());
    }
}
