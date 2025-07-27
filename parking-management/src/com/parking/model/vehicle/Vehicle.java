package com.parking.model.vehicle;

public abstract class Vehicle {
    private final String licensePlate;

    public Vehicle(String licensePlate) {
        if (licensePlate == null || licensePlate.isBlank()) {
            throw new IllegalArgumentException("License plate cannot be null or empty.");
        }
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public abstract VehicleType getVehicleType();
}