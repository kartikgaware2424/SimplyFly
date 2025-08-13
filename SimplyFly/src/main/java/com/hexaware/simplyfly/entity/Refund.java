package com.hexaware.simplyfly.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.hexaware.simplyfly.dto.BookingDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refundId;

    private double amount;

    private LocalDateTime refundDate = LocalDateTime.now();
    
    private String refundMethod; // e.g., UPI, Bank Transfer
    private String transactionId; // To track the payment gateway and  refund
    private String reason; // Flight cancelled,Will get it from User 


    @Enumerated(EnumType.STRING)
    private RefundStatus status; //  PENDING, COMPLETED, FAILED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // The passenger who initiated the refund

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", unique = true)
    private Booking booking;
}
