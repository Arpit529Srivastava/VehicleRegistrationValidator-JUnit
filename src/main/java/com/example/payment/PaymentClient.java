package com.example.payment;

public class PaymentClient {

    private final PaymentGateway gateway;

    public PaymentClient() {
        this.gateway = new PaymentGateway();
    }

    public PaymentClient(String provider) {
        this.gateway = new PaymentGateway(provider);
    }

    public PaymentClient(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public String process(double amount) {
        return gateway.makePayment(amount);
    }
}
