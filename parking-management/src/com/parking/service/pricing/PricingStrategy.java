package com.parking.service.pricing;

import com.parking.model.ticket.Ticket;
import com.parking.model.vehicle.VehicleType;
import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFee(Ticket ticket, LocalDateTime exitTime, VehicleType vehicleType);
}