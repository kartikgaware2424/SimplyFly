package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Seat;

public interface SeatService {
	Seat getSeatById(int id);

	List<Seat> getSeatsByFlight(int flightId);

	List<Seat> getSeatsByBookingStatus(boolean isBooked);

	Seat addSeat(Seat seat);
}
