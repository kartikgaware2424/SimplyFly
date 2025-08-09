package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class RefundDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    private double amount;

    private LocalDateTime refundDate = LocalDateTime.now();
    
    private String refundMethod; // e.g., UPI, Bank Transfer
    private String transactionId; // To track the payment gateway and  refund
    private String reason; // Flight cancelled,Will get it from User 


    @Enumerated(EnumType.STRING)
    private RefundStatusDto status; //  PENDING, COMPLETED, FAILED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDto user; // The passenger who initiated the refund

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private BookingDto booking;
}
