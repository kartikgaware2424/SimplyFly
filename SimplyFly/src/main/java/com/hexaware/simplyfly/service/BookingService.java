package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Booking;

public interface BookingService {
	Booking getBookingById(Long id);

	List<Booking> getBookingsByUser(Long userId);

	List<Booking> getBookingsByFlight(Long flightId);

	List<Booking> getBookingsByStatus(String status);

	Booking addBooking(Booking booking);
}
