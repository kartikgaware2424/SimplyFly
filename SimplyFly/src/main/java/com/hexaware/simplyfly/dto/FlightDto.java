package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class FlightDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;

	private String flightName;

	@Column(unique = true, nullable = false)
	private String flightNumber;

	private int totalSeats;
	private double fare;

	private String baggageCheckIn; // e.g., 20kg
	private String baggageCabin; // e.g., 7kg

	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;

	@ManyToOne
	@JoinColumn(name = "route_id", nullable = false)
	private RouteDto route;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private UserDto owner; // Flight owner

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private List<SeatDto> seats;
}