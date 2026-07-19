package com.example.banking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationServiceTest {

    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService();
    }

    @AfterEach
    void tearDown() {
        notificationService = null;
    }

    @Test
    @DisplayName("notifyDeposit returns a correctly formatted deposit success message")
    void notifyDeposit_returnsCorrectMessage() {
        String message = notificationService.notifyDeposit("Alice", 300.0);
        assertEquals("Deposit of 300.0 made successfully for Alice", message);
    }

    @Test
    @DisplayName("notifyWithdrawal returns a correctly formatted withdrawal success message")
    void notifyWithdrawal_returnsCorrectMessage() {
        String message = notificationService.notifyWithdrawal("Bob", 150.0);
        assertEquals("Withdrawal of 150.0 made successfully for Bob", message);
    }

    @Test
    @DisplayName("notifyInsufficientFunds returns a correctly formatted failure message")
    void notifyInsufficientFunds_returnsCorrectMessage() {
        String message = notificationService.notifyInsufficientFunds("Carol");
        assertEquals("Withdrawal failed due to insufficient funds for Carol", message);
    }
}
