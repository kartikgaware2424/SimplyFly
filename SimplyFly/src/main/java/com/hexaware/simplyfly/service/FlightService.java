package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Flight;

public interface FlightService {
	Flight getFlightById(int id);

	List<Flight> searchFlights(String origin, String destination);

	List<Flight> getFlightsByOwner(int ownerId);

	Flight addFlight(Flight flight);
}
