package com.hexaware.simplyfly.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.hexaware.simplyfly.dto.BookingDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Document {

    @Id
    @GeneratedValue
    private int documentId;

    private String documentType; // e.g., "AADHAAR", "PASSPORT"
    private String documentNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
