package com.parking.service.pricing;

import com.parking.model.ticket.Ticket;
import com.parking.model.vehicle.VehicleType;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy {
    private final Map<VehicleType, Double> hourlyRates;

    public HourlyPricingStrategy(Map<VehicleType, Double> hourlyRates) {
        this.hourlyRates = hourlyRates;
    }

    @Override
    public double calculateFee(Ticket ticket, LocalDateTime exitTime, VehicleType vehicleType) {
        Duration duration = Duration.between(ticket.entryTime(), exitTime);
        long hours = (long) Math.ceil(duration.toMinutes() / 60.0);
        if (hours == 0) {
            hours = 1; // Minimum charge is for 1 hour
        }
        double rate = hourlyRates.getOrDefault(vehicleType, 0.0);
        return hours * rate;
    }
}