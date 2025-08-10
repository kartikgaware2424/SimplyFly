package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.BookedSeatDto;
import com.hexaware.simplyfly.entity.BookedSeat;

public interface BookedSeatService {
	List<BookedSeat> getBookedSeatsByBooking(int bookingId);

	List<BookedSeat> getBookedSeatsByPassenger(int passengerId);

	BookedSeat addBookedSeatFromDto(BookedSeatDto dto);
}
