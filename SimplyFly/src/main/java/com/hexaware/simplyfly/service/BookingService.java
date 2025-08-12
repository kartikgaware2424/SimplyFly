package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.BookingDto;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;

public interface BookingService {
	Booking getBookingById(int id) throws BookingNotFoundException;

	List<Booking> getBookingsByUser(int userId) throws BookingNotFoundException;

	List<Booking> getBookingsByFlight(int flightId) throws BookingNotFoundException;

	List<Booking> getBookingsByStatus(String status) throws BookingNotFoundException;

	Booking addBooking(BookingDto bookingDto) throws BookingNotFoundException, FlightNotFoundException;
}
