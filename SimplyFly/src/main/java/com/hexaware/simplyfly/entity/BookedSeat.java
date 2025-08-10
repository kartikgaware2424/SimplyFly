package com.hexaware.simplyfly.entity;

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
public class BookedSeat {
    @Id @GeneratedValue
    private int BookSeatid;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger; // Passenger who have booked the seat

    private double price;
}