package com.example.payment;

import com.example.extension.LoggingExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(LoggingExtension.class)
class PaymentUtilTest {

    private PaymentUtil paymentUtil;

    @BeforeEach
    void setUp() {
        paymentUtil = new PaymentUtil();
    }

    @AfterEach
    void tearDown() {
        paymentUtil = null;
    }

    // ---------- maskCardNumber ----------

    @Test
    @DisplayName("A valid 16-digit card number is masked, revealing only the last 4 digits")
    void maskCardNumber_withValidSixteenDigitCard_returnsMaskedNumber() {
        assertEquals("**** **** **** 5678", paymentUtil.maskCardNumber("1234567812345678"));
    }

    @Test
    @DisplayName("A card number with exactly 4 digits is masked and fully revealed")
    void maskCardNumber_withExactlyFourDigits_returnsMaskedNumber() {
        assertEquals("**** **** **** 1234", paymentUtil.maskCardNumber("1234"));
    }

    @Test
    @DisplayName("A null card number returns the invalid card number message")
    void maskCardNumber_withNullInput_returnsInvalidMessage() {
        assertEquals("Invalid card number", paymentUtil.maskCardNumber(null));
    }

    @Test
    @DisplayName("An empty card number returns the invalid card number message")
    void maskCardNumber_withEmptyString_returnsInvalidMessage() {
        assertEquals("Invalid card number", paymentUtil.maskCardNumber(""));
    }

    @Test
    @DisplayName("A card number shorter than 4 digits returns the invalid card number message")
    void maskCardNumber_withFewerThanFourDigits_returnsInvalidMessage() {
        assertEquals("Invalid card number", paymentUtil.maskCardNumber("123"));
    }

    // ---------- isCardExpired ----------

    @Test
    @DisplayName("A date in the past is reported as expired")
    void isCardExpired_withPastDate_returnsTrue() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertTrue(paymentUtil.isCardExpired(pastDate));
    }

    @Test
    @DisplayName("A date in the future is reported as not expired")
    void isCardExpired_withFutureDate_returnsFalse() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertFalse(paymentUtil.isCardExpired(futureDate));
    }

    @Test
    @DisplayName("Today's date is not considered expired")
    void isCardExpired_withTodayDate_returnsFalse() {
        LocalDate today = LocalDate.now();
        assertFalse(paymentUtil.isCardExpired(today));
    }

    @Test
    @DisplayName("A null expiry date throws a NullPointerException")
    void isCardExpired_withNullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> paymentUtil.isCardExpired(null));
    }

    // ---------- formatCurrency ----------

    @Test
    @DisplayName("A positive amount is formatted with two decimal places and the currency symbol")
    void formatCurrency_withPositiveAmount_returnsFormattedString() {
        assertEquals("$100.50", paymentUtil.formatCurrency(100.5, "$"));
    }

    @Test
    @DisplayName("An amount is rounded to two decimal places")
    void formatCurrency_withRoundingNeeded_returnsRoundedString() {
        assertEquals("$100.00", paymentUtil.formatCurrency(99.999, "$"));
    }

    @Test
    @DisplayName("A zero amount is formatted correctly")
    void formatCurrency_withZeroAmount_returnsFormattedString() {
        assertEquals("€0.00", paymentUtil.formatCurrency(0.0, "\u20AC"));
    }

    @Test
    @DisplayName("A negative amount is formatted with the sign preserved")
    void formatCurrency_withNegativeAmount_returnsFormattedStringWithSign() {
        assertEquals("$-50.00", paymentUtil.formatCurrency(-50.0, "$"));
    }

    @Test
    @DisplayName("A null currency symbol throws a NullPointerException")
    void formatCurrency_withNullCurrencySymbol_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> paymentUtil.formatCurrency(100.0, null));
    }
}
