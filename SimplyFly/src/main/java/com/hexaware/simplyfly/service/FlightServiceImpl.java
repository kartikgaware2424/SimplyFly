package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightRepository flightRepo;

	public Flight addFlight(Flight flight) {
		return flightRepo.save(flight);
	}

	public Flight getFlightById(int id) {
		return flightRepo.findById(id).orElse(null);
	}

	public List<Flight> searchFlights(String origin, String destination) {
		return flightRepo.findByRouteOriginAndRouteDestination(origin, destination);
	}

	public List<Flight> getFlightsByOwner(int ownerId) {
		return flightRepo.findByOwnerUserId(ownerId);
	}

}
