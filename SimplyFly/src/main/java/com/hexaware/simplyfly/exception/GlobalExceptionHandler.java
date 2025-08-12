package com.hexaware.simplyfly.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(FlightNotFoundException.class)
	@ResponseStatus(reason = "Flight not found with the given details", code = HttpStatus.NOT_FOUND)
	public void handleFlightNotFound() {
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(reason = "User not found with the given details", code = HttpStatus.NOT_FOUND)
	public void handleUserNotFound() {
	}

	@ExceptionHandler(SeatNotAvailableException.class)
	@ResponseStatus(reason = "The selected seat is not available", code = HttpStatus.BAD_REQUEST)
	public void handleSeatNotAvailable() {
	}

	@ExceptionHandler(BookingNotFoundException.class)
	@ResponseStatus(reason = "Booking not found with the given ID", code = HttpStatus.NOT_FOUND)
	public void handleBookingNotFound() {
	}

	@ExceptionHandler(PaymentFailedException.class)
	@ResponseStatus(reason = "Payment process failed", code = HttpStatus.BAD_REQUEST)
	public void handlePaymentFailed() {
	}

	@ExceptionHandler(RefundNotFoundException.class)
	@ResponseStatus(reason = "Refund not found for the given booking", code = HttpStatus.NOT_FOUND)
	public void handleRefundNotFound() {
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(reason = "Input validation failed", code = HttpStatus.BAD_REQUEST)
	public void handleValidations() {

		log.error("validation failed");

	}

}
