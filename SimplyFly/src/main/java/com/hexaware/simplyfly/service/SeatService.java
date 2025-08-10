package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.SeatDto;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.exception.SeatNotAvailableException;

public interface SeatService {
	Seat getSeatById(int id) throws SeatNotAvailableException;

	List<Seat> getSeatsByFlight(int flightId) throws SeatNotAvailableException;

	List<Seat> getSeatsByBookingStatus(boolean isBooked) throws SeatNotAvailableException;

	Seat addSeat(SeatDto seatDto);

	List<Seat> getSeatsByFlightName(String flightName) throws SeatNotAvailableException;
}
