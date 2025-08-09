package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookingDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDateTime bookingDate;

    private double totalAmount;

    private String status; // "CONFIRMED", "CANCELLED", etc.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDto user; // Passenger who made the booking

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightDto flight;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<SeatDto> bookedSeats;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private PaymentDto payment;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private RefundDto refund;
}