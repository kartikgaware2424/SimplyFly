package com.hexaware.simplyfly.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.hexaware.simplyfly.dto.BookingDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;

    private String origin;      // e.g., Mumbai
    private String destination; // e.g., Delhi

    private double distanceInKm; //  Used to calculate the fare

    private String travelDuration; //e.g 2h 15m

    @OneToMany(mappedBy = "route")
    private List<Flight> flights;
}
