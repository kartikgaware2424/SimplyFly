package com.hexaware.simplyfly.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.hexaware.simplyfly.dto.BookingDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    private LocalDateTime bookingDate;

    private double totalAmount;

    private String status; // "CONFIRMED", "CANCELLED", etc.

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User passenger; // Passenger who made the booking

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> bookedSeats;


    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Refund refund;
}