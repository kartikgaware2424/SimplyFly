package com.hexaware.simplyfly.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.simplyfly.dto.SeatDto;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.SeatNotAvailableException;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.SeatRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional 
class SeatServiceImplTest {

	@Autowired
	private SeatService seatService;

	@Autowired
	private SeatRepository seatRepo;

	@Autowired
	private FlightRepository flightRepo;

	private Flight flight;

	@BeforeEach
	void setUp() {
		
		seatRepo.deleteAll();
        flightRepo.deleteAll();
		
		flight = new Flight();
		flight.setFlightName("TestFlight");
		flight.setFlightNumber("TF123");
		flight.setTotalSeats(100);
		flight.setFare(5000.0);
		flight = flightRepo.save(flight); 
	}

	@Test
	void addSeat_shouldAddSeatSuccessfully() throws FlightNotFoundException, BookingNotFoundException {
		SeatDto seatDto = new SeatDto("12A", false, "Economy", flight.getFlightId(), 0);
		Seat savedSeat = seatService.addSeat(seatDto);

		assertThat(savedSeat).isNotNull();
		
	}

	@Test
	void getSeatById_shouldReturnSeat()
			throws FlightNotFoundException, SeatNotAvailableException, BookingNotFoundException {
		SeatDto seatDto = new SeatDto("12B", false, "Business", flight.getFlightId(), 0);
		Seat savedSeat = seatService.addSeat(seatDto);

		Seat foundSeat = seatService.getSeatById(savedSeat.getSeatId());
		assertThat(foundSeat).isNotNull();
		
	}

	@Test
	void getSeatById_shouldThrowException_whenSeatNotFound() {
		assertThrows(SeatNotAvailableException.class, () -> {
			seatService.getSeatById(999); 
		});
	}
}
