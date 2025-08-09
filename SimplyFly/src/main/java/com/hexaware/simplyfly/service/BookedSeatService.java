package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.BookedSeat;

public interface BookedSeatService {
	List<BookedSeat> getBookedSeatsByBooking(Long bookingId);

	List<BookedSeat> getBookedSeatsByPassenger(Long passengerId);

	BookedSeat addBookedSeat(BookedSeat bookedSeat);
}
