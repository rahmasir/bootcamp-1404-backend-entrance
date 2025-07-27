package com.parking.model.spot;

import com.parking.model.vehicle.VehicleType;

public class ParkingSpot {
    private final int spotNumber;
    private final VehicleType spotType;
    private boolean isOccupied;

    public ParkingSpot(int spotNumber, VehicleType spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public int getSpotNumber() { return spotNumber; }
    public VehicleType getSpotType() { return spotType; }
    public boolean isOccupied() { return isOccupied; }
    public void occupy() { this.isOccupied = true; }
    public void vacate() { this.isOccupied = false; }
}