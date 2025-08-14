package com.hexaware.simplyfly.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.FlightDto;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.service.FlightService;

import jakarta.validation.Valid;
/**
 * This class handles flight Controller 
 * Features:
 * add flights
 * get flight by ID
 * search flight by routes
 * search flight by owner ID
 * get flight by owners
 *
 * @author Kartik Gaware
 * 
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	
	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public Flight addFlight(@RequestBody @Valid FlightDto flightDto) throws FlightNotFoundException {
		return flightService.addFlight(flightDto);
	}

	
	@GetMapping("getById/{id}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public Flight getFlightById(@PathVariable int id) throws FlightNotFoundException {
		return flightService.getFlightById(id);
	}

	
	@GetMapping("/searchByRoute/{origin}/{destination}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public List<Flight> searchFlights(@PathVariable String origin, @PathVariable String destination)
			throws FlightNotFoundException {
		return flightService.searchFlights(origin, destination);
	}
	
    @PutMapping("/updateById/{id}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public Flight updateFlight(@PathVariable int id, @RequestBody @Valid FlightDto flightDto)
            throws FlightNotFoundException {
        return flightService.updateFlight(id, flightDto);
    }

    
    @DeleteMapping("/deleteById/{id}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public String deleteFlight(@PathVariable int id) throws FlightNotFoundException {
        flightService.deleteFlight(id);
        return "Flight with ID " + id + " deleted successfully.";
    }

	
	@GetMapping("/searchByRouteAndDate/{origin}/{destination}/{departureDate}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public List<Flight> searchFlightsByDate(@PathVariable String origin, @PathVariable String destination,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate)
			throws FlightNotFoundException {
		return flightService.searchFlightsByDate(origin, destination, departureDate);
	}

	
	@GetMapping("/getByOwner/{ownerId}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public List<Flight> getFlightsByOwner(@PathVariable int ownerId) throws FlightNotFoundException {
		return flightService.getFlightsByOwner(ownerId);
	}
}