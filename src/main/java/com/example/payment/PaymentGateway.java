package com.example.payment;

public class PaymentGateway {

    private final String provider;

    public PaymentGateway(String provider) {
        this.provider = provider;
    }

    public PaymentGateway() {
        this.provider = "Cash";
    }

    public String makePayment(double amount) {
        return "Processed " + amount + " via " + provider;
    }
}
