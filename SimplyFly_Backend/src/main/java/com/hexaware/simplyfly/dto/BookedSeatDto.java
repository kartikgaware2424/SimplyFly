package com.hexaware.simplyfly.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookedSeatDto {
	@NotNull(message = "Booking ID cannot be null")
	@Min(value = 1, message = "Booking ID must be greater than or equal to 1")
	private int bookingId;

	@NotNull(message = "Seat ID cannot be null")
	@Min(value = 1, message = "Seat ID must be greater than or equal to 1")
	private int seatId;

	@NotNull(message = "Passenger ID cannot be null")
	@Min(value = 1, message = "Passenger ID must be greater than or equal to 1")
	private int passengerId;

	@Positive(message = "Price must be a positive value")
	@Max(value = 1000000, message = "Price cannot exceed 1,000,000")
	private double price;
}