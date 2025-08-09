package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Flight;

public interface FlightService {
	Flight getFlightById(Long id);

	List<Flight> searchFlights(String origin, String destination);

	List<Flight> getFlightsByOwner(Long ownerId);

	Flight addFlight(Flight flight);
}
