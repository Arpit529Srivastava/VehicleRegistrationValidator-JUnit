package com.example.hotel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 5 tests for {@link HotelStay#calculateStayCost(String, int)}.
 *
 * <p>Tests are explicitly ordered via {@link Order} so that valid room-type
 * scenarios run before the invalid-input scenarios.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HotelStayTest {

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("[SETUP] Starting test: " + testInfo.getDisplayName());
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("[CLEANUP] Finished test: " + testInfo.getDisplayName());
    }

    // ---------- Valid room type scenarios (run first) ----------

    @Test
    @Order(1)
    @DisplayName("Standard room for a positive number of nights returns 100 per night")
    void calculateStayCost_standardRoom_returnsCorrectCost() {
        assertEquals(300.0, HotelStay.calculateStayCost("Standard", 3));
    }

    @Test
    @Order(2)
    @DisplayName("Deluxe room for a positive number of nights returns 200 per night")
    void calculateStayCost_deluxeRoom_returnsCorrectCost() {
        assertEquals(600.0, HotelStay.calculateStayCost("Deluxe", 3));
    }

    @Test
    @Order(3)
    @DisplayName("Suite room for a positive number of nights returns 400 per night")
    void calculateStayCost_suiteRoom_returnsCorrectCost() {
        assertEquals(1200.0, HotelStay.calculateStayCost("Suite", 3));
    }

    @Test
    @Order(4)
    @DisplayName("Room type provided in lowercase is matched case-insensitively")
    void calculateStayCost_lowercaseRoomType_returnsCorrectCost() {
        assertEquals(200.0, HotelStay.calculateStayCost("standard", 2));
    }

    @Test
    @Order(5)
    @DisplayName("Room type provided in mixed case is matched case-insensitively")
    void calculateStayCost_mixedCaseRoomType_returnsCorrectCost() {
        assertEquals(800.0, HotelStay.calculateStayCost("DeLuXe", 4));
    }

    @Test
    @Order(6)
    @DisplayName("Room type provided in uppercase is matched case-insensitively")
    void calculateStayCost_uppercaseRoomType_returnsCorrectCost() {
        assertEquals(1600.0, HotelStay.calculateStayCost("SUITE", 4));
    }

    // ---------- Invalid input scenarios (run after valid room type tests) ----------

    @Test
    @Order(7)
    @DisplayName("An unrecognised room type returns 0")
    void calculateStayCost_invalidRoomType_returnsZero() {
        assertEquals(0.0, HotelStay.calculateStayCost("Economy", 3));
    }

    @Test
    @Order(8)
    @DisplayName("A negative number of nights returns 0 regardless of room type")
    void calculateStayCost_negativeNights_returnsZero() {
        assertEquals(0.0, HotelStay.calculateStayCost("Suite", -2));
    }

    @Test
    @Order(9)
    @DisplayName("Zero nights returns 0 regardless of room type")
    void calculateStayCost_zeroNights_returnsZero() {
        assertEquals(0.0, HotelStay.calculateStayCost("Deluxe", 0));
    }
}
