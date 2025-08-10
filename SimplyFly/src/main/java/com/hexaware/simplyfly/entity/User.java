package com.hexaware.simplyfly.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING) // roles are Passenger, FlightOwner and Admin
    private UserRole role;

    private String contactNumber;
    private String gender;
    private int age;
    private String address;

    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "owner")
    private List<Flight> ownedFlights;

    @OneToMany(mappedBy = "user")
    private List<Document> documents;

    @OneToMany(mappedBy = "passenger")
    private List<BookedSeat> bookedSeats;
}
