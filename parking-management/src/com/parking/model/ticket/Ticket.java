package com.parking.model.ticket;

import java.time.LocalDateTime;

public record Ticket(String licensePlate, int spotNumber, LocalDateTime entryTime) {}