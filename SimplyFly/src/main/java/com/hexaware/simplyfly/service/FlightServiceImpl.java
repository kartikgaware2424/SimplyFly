package com.hexaware.simplyfly.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.FlightDto;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.RouteRepository;
import com.hexaware.simplyfly.repository.UserRepository;
/**
 * Flight Service Implementation 
 * Logic:
 * Add flight
 * Get flight by ID
 * Get flight by owner
 * Search flight by date
 * Update and Delete flight
 * @author Kartik Gaware
 */
@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	private RouteRepository routeRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Flight addFlight(FlightDto flightDto) throws FlightNotFoundException {
		Flight flight = new Flight();
		flight.setFlightName(flightDto.getFlightName());
		flight.setFlightNumber(flightDto.getFlightNumber());
		flight.setTotalSeats(flightDto.getTotalSeats());
		flight.setFare(flightDto.getFare());
		flight.setBaggageCheckIn(flightDto.getBaggageCheckIn());
		flight.setBaggageCabin(flightDto.getBaggageCabin());
		flight.setDepartureDate(flightDto.getDepartureDate());
		flight.setDepartureTime(flightDto.getDepartureTime());
		flight.setArrivalTime(flightDto.getArrivalTime());
		flight.setRoute(routeRepo.findById(flightDto.getRouteId()).orElseThrow(() -> new FlightNotFoundException("Route not found with ID: " + flightDto.getRouteId())));

		flight.setOwner(userRepo.findById(flightDto.getOwnerId()).orElseThrow(() -> new FlightNotFoundException("Owner not found with ID: " + flightDto.getOwnerId())));;

		return flightRepo.save(flight);
	}

	@Override
	public Flight getFlightById(int id) throws FlightNotFoundException {
		return flightRepo.findById(id).orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + id));
	}

	@Override
	public List<Flight> searchFlights(String origin, String destination) throws FlightNotFoundException {
		List<Flight> flights = flightRepo.findByRouteOriginAndRouteDestination(origin, destination);
		if (flights.isEmpty())
			throw new FlightNotFoundException("No flights found from " + origin + " to " + destination);
		return flights;
	}

	@Override
	public List<Flight> searchFlightsByDate(String origin, String destination, LocalDate departureDate)
			throws FlightNotFoundException {
		List<Flight> flights = flightRepo.findByRouteOriginAndRouteDestinationAndDepartureDate(origin, destination,departureDate);
		if (flights.isEmpty())
			throw new FlightNotFoundException("No flights found from " + origin + " to " + destination + " on " + departureDate);
		return flights;
	}

	@Override
	public List<Flight> getFlightsByOwner(int ownerId) throws FlightNotFoundException {
		List<Flight> flights = flightRepo.findByOwnerUserId(ownerId);
		if (flights.isEmpty())
			throw new FlightNotFoundException("No flights found for owner with ID: " + ownerId);
		return flights;
	}

	@Override
	public Flight updateFlight(int id, FlightDto flightDto) throws FlightNotFoundException {
		Flight flight = getFlightById(id);
		
		
		flight.setFlightName(flightDto.getFlightName());
		flight.setFlightNumber(flightDto.getFlightNumber());
		flight.setTotalSeats(flightDto.getTotalSeats());
		flight.setFare(flightDto.getFare());
		flight.setBaggageCheckIn(flightDto.getBaggageCheckIn());
		flight.setBaggageCabin(flightDto.getBaggageCabin());
		flight.setDepartureDate(flightDto.getDepartureDate());
		flight.setDepartureTime(flightDto.getDepartureTime());
		flight.setArrivalTime(flightDto.getArrivalTime());
		flight.setRoute(routeRepo.findById(flightDto.getRouteId()).orElseThrow(() -> new FlightNotFoundException("Route not found with ID: " + flightDto.getRouteId())));

		flight.setOwner(userRepo.findById(flightDto.getOwnerId()).orElseThrow(() -> new FlightNotFoundException("Owner not found with ID: " + flightDto.getOwnerId())));
		;

		
		
		return flightRepo.save(flight);
	}

	@Override
	public String deleteFlight(int id) throws FlightNotFoundException {
		Flight flight = getFlightById(id);
		flightRepo.deleteById(id);
		return "Deleted Successfully:" +flight.getFlightName();
	}
}
