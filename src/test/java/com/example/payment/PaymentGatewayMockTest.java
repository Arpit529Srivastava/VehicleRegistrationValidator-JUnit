package com.example.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link PaymentClient} that mock {@link PaymentGateway} so that
 * no real payment provider logic is ever invoked.
 */
class PaymentGatewayMockTest {

    @Test
    @DisplayName("mockConstruction on the default constructor returns the stubbed fake result")
    void test01_mockDefaultConstructor() {
        try (MockedConstruction<PaymentGateway> mockedGateway = mockConstruction(PaymentGateway.class)) {
            PaymentClient client = new PaymentClient();

            PaymentGateway constructedMock = mockedGateway.constructed().get(0);
            when(constructedMock.makePayment(anyDouble())).thenReturn("Fake Cash Payment");

            String result = client.process(100.0);

            assertEquals("Fake Cash Payment", result);
        }
    }

    @Test
    @DisplayName("mockConstruction on the parameterised constructor returns the stubbed fake result")
    void test02_mockParameterizedConstructor() {
        try (MockedConstruction<PaymentGateway> mockedGateway = mockConstruction(PaymentGateway.class)) {
            PaymentClient client = new PaymentClient("Stripe");

            PaymentGateway constructedMock = mockedGateway.constructed().get(0);
            when(constructedMock.makePayment(anyDouble())).thenReturn("Simulated Stripe Response");

            String result = client.process(250.0);

            assertEquals("Simulated Stripe Response", result);
        }
    }

    @Test
    @DisplayName("Three PaymentClient instances trigger exactly three mocked PaymentGateway constructions")
    void test03_mockMultipleConstructors() {
        try (MockedConstruction<PaymentGateway> mockedGateway = mockConstruction(PaymentGateway.class)) {
            PaymentClient defaultClient = new PaymentClient();
            PaymentClient paypalClient = new PaymentClient("PayPal");
            PaymentClient stripeClient = new PaymentClient("Stripe");

            assertEquals(3, mockedGateway.constructed().size());
        }
    }

    @Test
    @DisplayName("A directly mocked PaymentGateway, injected into PaymentClient, returns the stubbed result")
    void test04_mockMethod() {
        PaymentGateway mockGateway = mock(PaymentGateway.class);
        when(mockGateway.makePayment(anyDouble())).thenReturn("Mocked Payment Success");

        PaymentClient client = new PaymentClient(mockGateway);

        String result = client.process(500.0);

        assertEquals("Mocked Payment Success", result);
    }
}
