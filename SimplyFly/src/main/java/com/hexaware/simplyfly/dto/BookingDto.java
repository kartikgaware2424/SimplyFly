package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {


	@FutureOrPresent
	private LocalDateTime bookingDate;

	@Positive
	private double totalAmount;

	
	@Pattern(regexp = "CONFIRMED|CANCELLED|PENDING", message = "Status must be CONFIRMED, CANCELLED, or PENDING")
	private String status;

	
	@Min(1)
	private int passengerId;

	
	@Min(1)
    private int flightId;

	private List<Integer> bookedSeatIds;

	private Integer paymentId;

	private Integer refundId;
}