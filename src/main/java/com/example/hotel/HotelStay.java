package com.example.hotel;

public class HotelStay {

    public static double calculateStayCost(String roomType, int nights) {
        if (nights < 1) {
            return 0;
        }
        if (roomType.equalsIgnoreCase("Standard")) {
            return 100 * nights;
        } else if (roomType.equalsIgnoreCase("Deluxe")) {
            return 200 * nights;
        } else if (roomType.equalsIgnoreCase("Suite")) {
            return 400 * nights;
        } else {
            return 0;
        }
    }
}
