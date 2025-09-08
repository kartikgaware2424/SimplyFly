package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.BookedSeatDto;
import com.hexaware.simplyfly.entity.BookedSeat;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.repository.BookedSeatRepository;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.SeatRepository;
import com.hexaware.simplyfly.repository.UserRepository;
/**
 * Booked Seat Service Implementation
 * Logic:
 * Get booked seat by booking
 * Get booked seat by passenger
 * @author Kartik Gaware
 */
@Service
public class BookedSeatServiceImpl implements BookedSeatService {

	@Autowired
	private BookedSeatRepository bookedSeatRepo;

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private SeatRepository seatRepo;

	@Autowired
	private UserRepository userRepo;

	

	@Override
	public List<BookedSeat> getBookedSeatsByBooking(int bookingId) {
		return bookedSeatRepo.findByBookingBookingId(bookingId);
	}

	@Override
	public List<BookedSeat> getBookedSeatsByPassenger(int passengerId) {
		return bookedSeatRepo.findByPassengerUserId(passengerId);
	}

}
