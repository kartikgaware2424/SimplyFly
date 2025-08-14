package com.hexaware.simplyfly.dto;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SeatDto {

	
	@Pattern(regexp = "^[0-9]{1,2}[A-Z]$", message = "Seat number must be in format like '12A'")
	
	private String seatNumber;

	@NotNull(message = "Booking status must be provided")
	private boolean booked;

	
	@Pattern(regexp = "Economy|Business|First", message = "Seat class must be Economy, Business, or First")
	private String seatClass;

	
	private int flightId;


	private int bookingId; // Can Optional because a seat might not be booked yet
}
