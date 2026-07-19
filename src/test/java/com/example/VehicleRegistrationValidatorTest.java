package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 5 test class for {@link VehicleRegistrationValidator}.
 * Verifies that validateVehicleRegistration correctly accepts registrations
 * matching the pattern "^[A-Z]{2}\d{2} \d{4}$" and rejects everything else.
 */
class VehicleRegistrationValidatorTest {

    private VehicleRegistrationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new VehicleRegistrationValidator();
    }

    @AfterEach
    void tearDown() {
        validator = null;
    }

    // ---------- Valid inputs ----------

    @Test
    @DisplayName("Standard valid registration should return true")
    void validRegistration_returnsTrue() {
        assertTrue(validator.validateVehicleRegistration("AB12 3456"));
    }

    @Test
    @DisplayName("Valid registration with different letters and digits should return true")
    void anotherValidRegistration_returnsTrue() {
        assertTrue(validator.validateVehicleRegistration("XY98 0001"));
    }

    @Test
    @DisplayName("Valid registration with all zero digits should return true")
    void validRegistrationWithZeroDigits_returnsTrue() {
        assertTrue(validator.validateVehicleRegistration("ZZ00 0000"));
    }

    // ---------- Invalid inputs ----------

    @Test
    @DisplayName("Registration missing the space separator should return false")
    void missingSpace_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration("AB123456"));
    }

    @Test
    @DisplayName("Registration with lowercase letters should return false")
    void lowercaseLetters_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration("ab12 3456"));
    }

    @Test
    @DisplayName("Registration with too few digits in the first group should return false")
    void tooFewDigitsInFirstGroup_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration("AB1 3456"));
    }

    @Test
    @DisplayName("Registration with too many digits in the second group should return false")
    void tooManyDigitsInSecondGroup_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration("AB12 34567"));
    }

    @Test
    @DisplayName("Registration with only one letter should return false")
    void onlyOneLetter_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration("A12 3456"));
    }

    @Test
    @DisplayName("Registration with extra letters should return false")
    void extraLetters_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration("ABC12 3456"));
    }

    @Test
    @DisplayName("Registration with digits instead of letters should return false")
    void digitsInsteadOfLetters_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration("12AB 3456"));
    }

    @Test
    @DisplayName("Empty string should return false")
    void emptyString_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration(""));
    }

    @Test
    @DisplayName("Registration with leading or trailing whitespace should return false")
    void leadingOrTrailingWhitespace_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration(" AB12 3456"));
        assertFalse(validator.validateVehicleRegistration("AB12 3456 "));
    }

    @Test
    @DisplayName("Registration with a hyphen instead of a space should return false")
    void hyphenInsteadOfSpace_returnsFalse() {
        assertFalse(validator.validateVehicleRegistration("AB12-3456"));
    }
}
