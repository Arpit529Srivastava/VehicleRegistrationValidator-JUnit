package com.example.ridesharing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 * JUnit 5 tests for {@link CarUtil} that stub both its static and final
 * methods with Mockito Core (v5+), without exercising the real switch logic.
 */
class CarUtilTest {

    @Test
    @DisplayName("Static method getCarType is mocked to return fake values per subscription level")
    void testGetCarType_StaticMocking() {
        try (MockedStatic<CarUtil> mockedCarUtil = mockStatic(CarUtil.class)) {
            mockedCarUtil.when(() -> CarUtil.getCarType("basic")).thenReturn("Fake Economy Car");
            mockedCarUtil.when(() -> CarUtil.getCarType("premium")).thenReturn("Fake SUV Ride");
            mockedCarUtil.when(() -> CarUtil.getCarType("luxury")).thenReturn("Fake Luxury Cruiser");

            assertEquals("Fake Economy Car", CarUtil.getCarType("basic"));
            assertEquals("Fake SUV Ride", CarUtil.getCarType("premium"));
            assertEquals("Fake Luxury Cruiser", CarUtil.getCarType("luxury"));
        }
    }

    @Test
    @DisplayName("Final method getCarBrand is mocked to return fake brands per fleet company")
    void testGetCarBrand_FinalMocking() {
        CarUtil mockCarUtil = mock(CarUtil.class);

        when(mockCarUtil.getCarBrand("fleetA")).thenReturn("Simulated Ford");
        when(mockCarUtil.getCarBrand("fleetB")).thenReturn("Simulated Hyundai");
        when(mockCarUtil.getCarBrand("fleetC")).thenReturn("Simulated Toyota");

        assertEquals("Simulated Ford", mockCarUtil.getCarBrand("fleetA"));
        assertEquals("Simulated Hyundai", mockCarUtil.getCarBrand("fleetB"));
        assertEquals("Simulated Toyota", mockCarUtil.getCarBrand("fleetC"));
    }
}
