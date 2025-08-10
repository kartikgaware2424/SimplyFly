package com.hexaware.simplyfly.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.FlightDto;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.service.FlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	// Add flight
	@PostMapping("/add")
	public Flight addFlight(@RequestBody @Valid FlightDto flightDto) throws FlightNotFoundException {
		return flightService.addFlight(flightDto);
	}

	// Get flight by ID
	@GetMapping("/{id}")
	public Flight getFlightById(@PathVariable int id) throws FlightNotFoundException {
		return flightService.getFlightById(id);
	}

	// Search flights by origin & destination
	@GetMapping("/search/{origin}/{destination}")
	public List<Flight> searchFlights(@PathVariable String origin, @PathVariable String destination)
			throws FlightNotFoundException {
		return flightService.searchFlights(origin, destination);
	}

	// Search flights by origin, destination & date
	@GetMapping("/search/{origin}/{destination}/{departureDate}")
	public List<Flight> searchFlightsByDate(@PathVariable String origin, @PathVariable String destination,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate)
			throws FlightNotFoundException {
		return flightService.searchFlightsByDate(origin, destination, departureDate);
	}

	// Get flights by owner
	@GetMapping("/owner/{ownerId}")
	public List<Flight> getFlightsByOwner(@PathVariable int ownerId) throws FlightNotFoundException {
		return flightService.getFlightsByOwner(ownerId);
	}
}