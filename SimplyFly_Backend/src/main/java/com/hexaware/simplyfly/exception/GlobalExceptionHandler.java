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
		log.error("Couldn't find the flight with the provided details.");
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(reason = "User not found with the given details", code = HttpStatus.NOT_FOUND)
	public void handleUserNotFound() {
		log.error("User search came up empty. No matching user found.");
	}

	@ExceptionHandler(SeatNotAvailableException.class)
	@ResponseStatus(reason = "The selected seat is not available", code = HttpStatus.BAD_REQUEST)
	public void handleSeatNotAvailable() {
		log.error("That seat was taken before you could grab it!");
	}

	@ExceptionHandler(BookingNotFoundException.class)
	@ResponseStatus(reason = "Booking not found with the given ID", code = HttpStatus.NOT_FOUND)
	public void handleBookingNotFound() {

		log.error("Booking lookup failed — no booking exists for this ID.");
	}

	@ExceptionHandler(PaymentFailedException.class)
	@ResponseStatus(reason = "Payment process failed", code = HttpStatus.BAD_REQUEST)
	public void handlePaymentFailed() {

		log.error("Something went wrong during the payment process.");
	}

	@ExceptionHandler(RefundNotFoundException.class)
	@ResponseStatus(reason = "Refund not found for the given booking", code = HttpStatus.NOT_FOUND)
	public void handleRefundNotFound() {
		  log.error("No refund found for this booking. Maybe it hasn’t been processed yet?");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleValidations(MethodArgumentNotValidException e) {
		  log.error("Input Validation Failed.Please try with Right inputs.");
		
	}

}
