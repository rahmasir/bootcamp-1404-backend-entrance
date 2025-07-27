package com.parking.service;

import com.parking.model.spot.ParkingSpot;
import com.parking.model.ticket.Ticket;
import com.parking.model.vehicle.Vehicle;
import com.parking.model.vehicle.VehicleType;
import com.parking.service.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private final List<ParkingSpot> spots;
    private final Map<String, Ticket> parkedVehicles;
    private final PricingStrategy pricingStrategy;

    public ParkingLot(List<ParkingSpot> spots, PricingStrategy pricingStrategy) {
        this.spots = spots;
        this.pricingStrategy = pricingStrategy;
        this.parkedVehicles = new ConcurrentHashMap<>();
    }

    public Optional<Ticket> parkVehicle(Vehicle vehicle) {
        if (parkedVehicles.containsKey(vehicle.getLicensePlate())) {
            System.err.println("Error: Vehicle with license plate " + vehicle.getLicensePlate() + " is already parked.");
            return Optional.empty();
        }

        Optional<ParkingSpot> availableSpot = spots.stream()
                .filter(spot -> spot.getSpotType() == vehicle.getVehicleType() && !spot.isOccupied())
                .findFirst();

        return availableSpot.map(spot -> {
            spot.occupy();
            Ticket ticket = new Ticket(vehicle.getLicensePlate(), spot.getSpotNumber(), LocalDateTime.now());
            parkedVehicles.put(vehicle.getLicensePlate(), ticket);
            return ticket;
        });
    }

    public double unparkVehicle(String licensePlate, VehicleType vehicleType, LocalDateTime exitTime) {
        Ticket ticket = parkedVehicles.get(licensePlate);
        if (ticket == null) {
            throw new IllegalArgumentException("Error: Vehicle with license plate " + licensePlate + " not found.");
        }

        double fee = pricingStrategy.calculateFee(ticket, exitTime, vehicleType);

        spots.stream()
                .filter(spot -> spot.getSpotNumber() == ticket.spotNumber())
                .findFirst()
                .ifPresent(ParkingSpot::vacate);

        parkedVehicles.remove(licensePlate);
        return fee;
    }

    public void displayStatus() {
        long occupiedCount = spots.stream().filter(ParkingSpot::isOccupied).count();
        System.out.println("\n----- Parking Lot Status -----");
        System.out.println("Total Spots: " + spots.size());
        System.out.println("Occupied Spots: " + occupiedCount);
        System.out.println("Available Spots: " + (spots.size() - occupiedCount));
        System.out.println("----------------------------\n");
    }
}