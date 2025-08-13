package com.hexaware.simplyfly.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.simplyfly.dto.FlightDto;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.entity.Route;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.RouteRepository;
import com.hexaware.simplyfly.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional 
class FlightServiceImplTest {

	@Autowired
	private FlightService flightService;

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	private RouteRepository routeRepo;

	@Autowired
	private UserRepository userRepo;

	private Route route;
	private User owner;

	@BeforeEach
	void setUp() {
		
		flightRepo.deleteAll();
		routeRepo.deleteAll();
		userRepo.deleteAll();

		// Create test route
		route = new Route();
		route.setOrigin("Mumbai");
		route.setDestination("Delhi");
		route.setDistanceInKm(1400);
		route.setTravelDuration("2h 30m");
		route = routeRepo.save(route);

	
		owner = new User();
		owner.setName("Flight Owner");
		owner.setEmail("owner@example.com");
		owner.setPassword("password");
		owner = userRepo.save(owner);
	}

	@Test
	void addFlight_shouldSaveFlightSuccessfully() throws FlightNotFoundException {
		FlightDto dto = new FlightDto("Test Flight", "TF123", 100, 5000.0, "20kg", "7kg", LocalDate.now().plusDays(1),
				LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), route.getRouteId(),
				owner.getUserId());

		Flight savedFlight = flightService.addFlight(dto);
		assertThat(savedFlight).isNotNull();
		
	}

	@Test
	void getFlightById_shouldReturnFlight() throws FlightNotFoundException {
		FlightDto dto = new FlightDto("Flight 2", "F200", 150, 6000.0, "25kg", "8kg", LocalDate.now().plusDays(1),
				LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(3), route.getRouteId(),
				owner.getUserId());

		Flight saved = flightService.addFlight(dto);
		Flight found = flightService.getFlightById(saved.getFlightId());
		assertEquals(saved.getFlightId(), found.getFlightId());
	}

	@Test
	void searchFlights_shouldReturnFlights() throws FlightNotFoundException {
		FlightDto dto = new FlightDto("Flight 3", "F300", 120, 5500.0, "20kg", "7kg", LocalDate.now().plusDays(1),
				LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), route.getRouteId(),
				owner.getUserId());
		flightService.addFlight(dto);

		List<Flight> flights = flightService.searchFlights("Mumbai", "Delhi");
		assertThat(flights).isNotEmpty();
	}

	@Test
	void updateFlight_shouldUpdateFlight() throws FlightNotFoundException {
		FlightDto dto = new FlightDto("Flight 4", "F400", 120, 5000.0, "20kg", "7kg", LocalDate.now().plusDays(1),
				LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), route.getRouteId(),
				owner.getUserId());
		Flight flight = flightService.addFlight(dto);

		FlightDto updateDto = new FlightDto("Updated Flight", "UF400", 130, 5200.0, "22kg", "8kg",
				LocalDate.now().plusDays(2), LocalDateTime.now().plusDays(2),
				LocalDateTime.now().plusDays(2).plusHours(3), route.getRouteId(), owner.getUserId());

		Flight updatedFlight = flightService.updateFlight(flight.getFlightId(), updateDto);
		assertEquals("UF400", updatedFlight.getFlightNumber());
		
	}

	@Test
	void deleteFlight_shouldRemoveFlight() throws FlightNotFoundException {
		FlightDto dto = new FlightDto("Flight 5", "F500", 100, 4800.0, "20kg", "7kg", LocalDate.now().plusDays(1),
				LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), route.getRouteId(),
				owner.getUserId());
		Flight flight = flightService.addFlight(dto);

		String message = flightService.deleteFlight(flight.getFlightId());
		assertThat(message).contains("Deleted Successfully");
		
	}
}