package com.example.ridesharing;

public class CarUtil {

    public static String getCarType(String subscriptionLevel) {
        switch (subscriptionLevel.toLowerCase()) {
            case "basic":
                return "Economy";
            case "premium":
                return "SUV";
            case "luxury":
                return "Luxury";
            default:
                return "Standard";
        }
    }

    public final String getCarBrand(String fleetCompany) {
        switch (fleetCompany.toLowerCase()) {
            case "fleeta":
                return "Ford";
            case "fleetb":
                return "Hyundai";
            case "fleetc":
                return "Toyota";
            default:
                return "Ferrari";
        }
    }
}
