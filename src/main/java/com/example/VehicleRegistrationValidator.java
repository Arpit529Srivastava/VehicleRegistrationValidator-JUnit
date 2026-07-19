package com.example;

public class VehicleRegistrationValidator {

    public boolean validateVehicleRegistration(String registration) {
        String pattern = "^[A-Z]{2}\\d{2} \\d{4}$";

        if (registration.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }
}
