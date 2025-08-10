package com.hexaware.simplyfly.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.simplyfly.dto.FlightDto;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.exception.FlightNotFoundException;

public interface FlightService {
	Flight getFlightById(int id) throws FlightNotFoundException;

	List<Flight> searchFlights(String origin, String destination) throws FlightNotFoundException;

	List<Flight> getFlightsByOwner(int ownerId) throws FlightNotFoundException;

	 Flight addFlight(FlightDto flightDto) throws FlightNotFoundException;

	List<Flight> searchFlightsByDate(String origin, String destination, LocalDate departureDate)
			throws FlightNotFoundException;
}
