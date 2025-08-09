package com.hexaware.simplyfly.dto;

import jakarta.persistence.*;

@Entity
public class DocumentDto {

    @Id
    @GeneratedValue
    private Long documentId;

    private String documentType; // e.g., "AADHAAR", "PASSPORT"
    private String documentNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDto user;
}
