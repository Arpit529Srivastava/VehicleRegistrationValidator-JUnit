package com.example.banking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("ACC123", "John Doe", 1000.0);
    }

    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    @DisplayName("getAccountNumber returns the account number supplied at construction")
    void getAccountNumber_returnsCorrectValue() {
        assertEquals("ACC123", account.getAccountNumber());
    }

    @Test
    @DisplayName("getAccountHolder returns the account holder name supplied at construction")
    void getAccountHolder_returnsCorrectValue() {
        assertEquals("John Doe", account.getAccountHolder());
    }

    @Test
    @DisplayName("getBalance returns the initial balance supplied at construction")
    void getBalance_returnsInitialBalance() {
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    @DisplayName("Depositing a positive amount increases the balance")
    void deposit_increasesBalance() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    @DisplayName("Depositing a negative amount still decreases the balance since deposit has no guard")
    void deposit_withNegativeAmount_decreasesBalance() {
        account.deposit(-200.0);
        assertEquals(800.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdrawing a valid amount succeeds and reduces the balance")
    void withdraw_withSufficientFunds_returnsTrueAndReducesBalance() {
        boolean result = account.withdraw(400.0);
        assertTrue(result);
        assertEquals(600.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdrawing more than the balance fails and leaves balance unchanged")
    void withdraw_withInsufficientFunds_returnsFalse() {
        boolean result = account.withdraw(5000.0);
        assertFalse(result);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdrawing a negative amount fails and leaves balance unchanged")
    void withdraw_withNegativeAmount_returnsFalse() {
        boolean result = account.withdraw(-100.0);
        assertFalse(result);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdrawing the exact balance succeeds and results in a zero balance")
    void withdraw_exactBalance_returnsTrueAndZeroesBalance() {
        boolean result = account.withdraw(1000.0);
        assertTrue(result);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdrawing zero fails since the amount must be strictly positive")
    void withdraw_zeroAmount_returnsFalse() {
        boolean result = account.withdraw(0.0);
        assertFalse(result);
        assertEquals(1000.0, account.getBalance());
    }
}
