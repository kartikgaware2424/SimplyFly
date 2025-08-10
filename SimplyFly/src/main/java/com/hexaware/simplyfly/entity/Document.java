package com.hexaware.simplyfly.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
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
