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

import com.hexaware.simplyfly.dto.SeatDto;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.SeatNotAvailableException;
import com.hexaware.simplyfly.service.SeatService;

import jakarta.validation.Valid;

/**
 * This class handles seat Controller Features: add seat get seat by ID get seat
 * by flight get seat by status get seat By flight name
 * 
 * @author Kartik Gaware
 * 
 */
@RestController
@RequestMapping("/api/seats")
public class SeatController {

	@Autowired
	private SeatService seatService;

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN','PASSENGER')")
	public Seat addSeat(@Valid @RequestBody SeatDto seatDto) throws FlightNotFoundException, BookingNotFoundException {
		return seatService.addSeat(seatDto);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public Seat getSeatById(@PathVariable int id) throws SeatNotAvailableException {
		return seatService.getSeatById(id);
	}

	@GetMapping("/getflightId/{flightId}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public List<Seat> getSeatsByFlight(@PathVariable int flightId) throws SeatNotAvailableException {
		return seatService.getSeatsByFlight(flightId);
	}

	@GetMapping("/getByStatus/{isBooked}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public List<Seat> getSeatsByBookingStatus(@PathVariable boolean isBooked) throws SeatNotAvailableException {
		return seatService.getSeatsByBookingStatus(isBooked);
	}

	@GetMapping("/getByFlight/name/{flightName}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public List<Seat> getSeatsByFlightName(@PathVariable String flightName) throws SeatNotAvailableException {
		return seatService.getSeatsByFlightName(flightName);
	}

	@GetMapping("/getBySeatNumber/{seatNumber}")
	public List<Seat> getSeatByNumber(@PathVariable String seatNumber) throws SeatNotAvailableException {
		return seatService.getSeatsBySeatNumber(seatNumber);
	}

}
