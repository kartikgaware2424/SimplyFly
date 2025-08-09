package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Booking;

public interface BookingService {
	Booking getBookingById(int id);

	List<Booking> getBookingsByUser(int userId);

	List<Booking> getBookingsByFlight(int flightId);

	List<Booking> getBookingsByStatus(String status);

	Booking addBooking(Booking booking);
}
