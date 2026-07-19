package com.example.payment;

import java.time.LocalDate;

public class PaymentUtil {

    public String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "Invalid card number";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    public boolean isCardExpired(LocalDate expiryDate) {
        if (expiryDate == null) throw new NullPointerException("Expiry date cannot be null");
        return expiryDate.isBefore(LocalDate.now());
    }

    public String formatCurrency(double amount, String currencySymbol) {
        if (currencySymbol == null) throw new NullPointerException("Currency symbol cannot be null");
        return currencySymbol + String.format("%.2f", amount);
    }
}
