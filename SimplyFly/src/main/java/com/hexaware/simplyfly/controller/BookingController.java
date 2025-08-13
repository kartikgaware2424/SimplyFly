package com.hexaware.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.BookingDto;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
	public Booking addBooking(@Valid @RequestBody BookingDto bookingDto)
			throws BookingNotFoundException, FlightNotFoundException {
		return bookingService.addBooking(bookingDto);
	}

	@GetMapping("/getById/{id}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public Booking getBookingById(@PathVariable int id) throws BookingNotFoundException {
		return bookingService.getBookingById(id);
	}

	@GetMapping("/getByUser/{userId}")
	@PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
	public List<Booking> getBookingsByUser(@PathVariable int userId) throws BookingNotFoundException {
		return bookingService.getBookingsByUser(userId);
	}

	@GetMapping("/getByFlight/{flightId}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public List<Booking> getBookingsByFlight(@PathVariable int flightId) throws BookingNotFoundException {
		return bookingService.getBookingsByFlight(flightId);
	}

	@GetMapping("/getByStatus/{status}")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Booking> getBookingsByStatus(@PathVariable String status) throws BookingNotFoundException {
		return bookingService.getBookingsByStatus(status);
	}

}