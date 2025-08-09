package com.hexaware.simplyfly.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class Seat {

    @Id
    @GeneratedValue
    private int seatId;

    private String seatNumber; // e.g 12A
    private boolean isBooked;
    private String seatClass; // Economy, Business, First


    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
