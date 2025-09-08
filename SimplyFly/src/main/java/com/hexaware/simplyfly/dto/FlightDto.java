package com.hexaware.simplyfly.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

	@Size(max = 100, message = "Flight name cannot exceed 100 characters.")
	@NotBlank(message = "Flight name is required.")
	private String flightName;

	@Size(max = 20, message = "Flight number cannot exceed 20 characters.")
	@NotBlank(message = "Flight number is required.")
	private String flightNumber;

	@Min(value = 1, message = "Total seats must be at least 1.")
	@Max(value = 1000, message = "Total seats cannot exceed 1000.")
	@NotNull(message = "Total seats is required.")
	private Integer totalSeats;

	@Positive(message = "Fare must be a positive value.")
	@Max(value = 100000, message = "Fare cannot exceed 100,000.")
	@NotNull(message = "Fare is required.")
	private Double fare;

	@Size(max = 20, message = "Baggage check-in description cannot exceed 20 characters.")
	@NotBlank(message = "Baggage check-in information is required.")
	private String baggageCheckIn;

	@Size(max = 20, message = "Baggage cabin description cannot exceed 20 characters.")
	@NotBlank(message = "Baggage cabin information is required.")
	private String baggageCabin;

	@FutureOrPresent(message = "Departure date must be today or in the future.")
	@NotNull(message = "Departure date is required.")
	private LocalDate departureDate;

	@FutureOrPresent(message = "Departure time must be today or in the future.")
	@NotNull(message = "Departure time is required.")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime departureTime;

	@FutureOrPresent(message = "Arrival time must be today or in the future.")
	@NotNull(message = "Arrival time is required.")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime arrivalTime;

	
	private Integer routeId;

	private Integer ownerId;
}