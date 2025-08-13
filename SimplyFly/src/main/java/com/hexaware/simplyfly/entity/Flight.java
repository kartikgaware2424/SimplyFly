package com.hexaware.simplyfly.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.hexaware.simplyfly.dto.BookingDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightId;

	private String flightName;

	@Column(unique = true)
	private String flightNumber;

	private int totalSeats;
	private double fare;

	private String baggageCheckIn; // e.g., 20kg
	private String baggageCabin; // e.g., 7kg
	private LocalDate departureDate; 
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;

	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner; // Flight owner

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private List<Seat> seats;
}