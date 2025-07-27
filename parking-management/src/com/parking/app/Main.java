package com.parking.app;

import com.parking.model.spot.ParkingSpot;
import com.parking.model.ticket.Ticket;
import com.parking.model.vehicle.*;
import com.parking.service.ParkingLot;
import com.parking.service.pricing.HourlyPricingStrategy;
import com.parking.service.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Smart Parking System...");

        // 1. Define Pricing Rules
        Map<VehicleType, Double> rates = Map.of(
                VehicleType.MOTORCYCLE, 1.0,
                VehicleType.CAR, 2.0,
                VehicleType.TRUCK, 3.0
        );
        PricingStrategy hourlyPricing = new HourlyPricingStrategy(rates); // dependency injection

        // 2. Create Parking Spots
        List<ParkingSpot> spots = List.of(
                new ParkingSpot(1, VehicleType.MOTORCYCLE),
                new ParkingSpot(2, VehicleType.MOTORCYCLE),
                new ParkingSpot(3, VehicleType.CAR),
                new ParkingSpot(4, VehicleType.CAR),
                new ParkingSpot(5, VehicleType.TRUCK)
        );

        // 3. Create the Parking Lot
        ParkingLot parkingLot = new ParkingLot(spots, hourlyPricing);
        parkingLot.displayStatus();

        // 4. Create Vehicles
        Vehicle myCar = new Car("CAR-123");
        Vehicle myMotorcycle = new Motorcycle("MOTO-456");

        // --- Simulate Parking ---
        System.out.println("--- Vehicle Entry Simulation ---");

        Optional<Ticket> carTicketOpt = parkingLot.parkVehicle(myCar);
        carTicketOpt.ifPresentOrElse(
                ticket -> System.out.println("Car parked successfully! Ticket: " + ticket),
                () -> System.err.println("Failed to park car.")
        );

        Optional<Ticket> motoTicketOpt = parkingLot.parkVehicle(myMotorcycle);
        motoTicketOpt.ifPresentOrElse(
                ticket -> System.out.println("Motorcycle parked successfully! Ticket: " + ticket),
                () -> System.err.println("Failed to park motorcycle.")
        );
        parkingLot.displayStatus();

        // --- Simulate Exit ---
        System.out.println("--- Vehicle Exit Simulation ---");

        carTicketOpt.ifPresent(ticket -> {
            LocalDateTime exitTime = ticket.entryTime().plusHours(2).plusMinutes(30);
            double fee = parkingLot.unparkVehicle(myCar.getLicensePlate(), myCar.getVehicleType(), exitTime);
            System.out.printf("Car %s leaving. Fee: $%.2f%n", myCar.getLicensePlate(), fee);
        });

        motoTicketOpt.ifPresent(ticket -> {
            LocalDateTime exitTime = ticket.entryTime().plusMinutes(55);
            double fee = parkingLot.unparkVehicle(myMotorcycle.getLicensePlate(), myMotorcycle.getVehicleType(), exitTime);
            System.out.printf("Motorcycle %s leaving. Fee: $%.2f%n", myMotorcycle.getLicensePlate(), fee);
        });

        parkingLot.displayStatus();
    }
}