package com.hexaware.simplyfly.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class UserDto {

    @Id
    @GeneratedValue
    private Long userId;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoleDto role;

    private String contactNumber;
    private String gender;
    private String address;

    @OneToMany(mappedBy = "user")
    private List<BookingDto> bookings;

    @OneToMany(mappedBy = "owner")
    private List<FlightDto> ownedFlights;

    @OneToMany(mappedBy = "user")
    private List<DocumentDto> documents;

    @OneToMany(mappedBy = "passenger")
    private List<BookedSeatDto> bookedSeats;
}
