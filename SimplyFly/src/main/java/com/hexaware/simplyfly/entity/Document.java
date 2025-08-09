package com.hexaware.simplyfly.entity;

import jakarta.persistence.*;

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
