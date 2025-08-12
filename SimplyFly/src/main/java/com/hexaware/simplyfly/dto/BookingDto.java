package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDto {

	@NotNull
	@FutureOrPresent
	private LocalDateTime bookingDate;

	@Positive
	private double totalAmount;

	@NotNull
	@Pattern(regexp = "CONFIRMED|CANCELLED|PENDING", message = "Status must be CONFIRMED, CANCELLED, or PENDING")
	private String status;

	@NotNull
	@Min(1)
	private int passengerId;

	@NotNull
	@Min(1)
    private int flightId;

	private List<Integer> bookedSeatIds;

	private Integer paymentId;

	private Integer refundId;
}