package com.hexaware.simplyfly.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class BookedSeatDto {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingDto booking;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private SeatDto seat;
    
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private UserDto passenger; // Passenger who have booked the seat

    private double price;
}