package com.hexaware.simplyfly.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat {

    @Id
    @GeneratedValue
    private int seatId;

    private String seatNumber; // e.g 12A
    private boolean isBooked; // True or False
    private String seatClass; // Economy, Business, First


    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference
    private Booking booking;
}
