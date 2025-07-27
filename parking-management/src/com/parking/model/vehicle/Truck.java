package com.parking.model.vehicle;

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.TRUCK;
    }
}