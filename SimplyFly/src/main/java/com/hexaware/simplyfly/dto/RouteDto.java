package com.hexaware.simplyfly.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class RouteDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private String origin;      // e.g., Mumbai
    private String destination; // e.g., Delhi

    private double distanceInKm; //  Used to calculate the fare

    private String travelDuration; //e.g 2h 15m

    @OneToMany(mappedBy = "route")
    private List<FlightDto> flights;
}
