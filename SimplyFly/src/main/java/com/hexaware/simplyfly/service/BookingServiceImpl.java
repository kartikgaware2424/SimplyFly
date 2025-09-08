package com.hexaware.simplyfly.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.BookingDto;
import com.hexaware.simplyfly.dto.RefundDto;
import com.hexaware.simplyfly.entity.BookedSeat;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.repository.BookedSeatRepository;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.SeatRepository;
import com.hexaware.simplyfly.repository.UserRepository;

import jakarta.transaction.Transactional;
/**
 * Booking Service Implementation 
 * Logic:
 * Add booking
 * Get booking by Id
 * Get booking by flight
 * Get booking by user
 * Get booking by status
 * @author Kartik Gaware
 */
@Transactional
@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	FlightRepository flightRepo;

	@Autowired
	BookedSeatRepository bookedSeatRepo;

	@Autowired
	SeatRepository seatRepo;
	
	@Autowired
	private RefundService refundService;

	@Override
	public Booking addBooking(BookingDto dto) throws BookingNotFoundException, FlightNotFoundException {
		User user = userRepo.findById(dto.getPassengerId()).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getPassengerId()));

		Flight flight = flightRepo.findById(dto.getFlightId()).orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + dto.getFlightId()));

		Booking booking = new Booking();
		booking.setBookingDate(dto.getBookingDate());
		booking.setTotalAmount(dto.getTotalAmount());
		booking.setStatus(dto.getStatus().toUpperCase());
		booking.setPassenger(user);
		booking.setFlight(flight);

		Booking savedBooking = bookingRepo.save(booking);

		if (dto.getBookedSeatIds() != null && !dto.getBookedSeatIds().isEmpty()) {
			for (Integer seatId : dto.getBookedSeatIds()) {
				Seat seat = seatRepo.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found with ID: " + seatId));
				seat.setBooking(savedBooking);
				seat.setBooked(true);
				seatRepo.save(seat);

				
				BookedSeat bookedSeat = new BookedSeat();
				bookedSeat.setBooking(savedBooking);
				bookedSeat.setSeat(seat);
				bookedSeat.setPassenger(user);
				bookedSeat.setPrice(dto.getTotalAmount()); 

				bookedSeatRepo.save(bookedSeat);
			}
		}

		return savedBooking;
	}

	@Override
	public Booking getBookingById(int id) throws BookingNotFoundException {
		return bookingRepo.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
	}

	@Override
	public List<Booking> getBookingsByUser(int userId) throws BookingNotFoundException {
		List<Booking> bookings = bookingRepo.findByPassengerUserId(userId);
		if (bookings.isEmpty()) {
			throw new BookingNotFoundException("No bookings found for user with ID: " + userId);
		}
		return bookings;
	}

	@Override
	public List<Booking> getBookingsByFlight(int flightId) throws BookingNotFoundException {
		List<Booking> bookings = bookingRepo.findByFlightFlightId(flightId);
		if (bookings.isEmpty()) {
			throw new BookingNotFoundException("No bookings found for flight with ID: " + flightId);
		}
		return bookings;
	}

	@Override
	public List<Booking> getBookingsByStatus(String status) throws BookingNotFoundException {
		List<Booking> bookings = bookingRepo.findByStatus(status);
		if (bookings.isEmpty()) {
			throw new BookingNotFoundException("No bookings found with status: " + status);
		}
		return bookings;
	}
	
//	public String cancelBooking(int bookingId) throws BookingNotFoundException {
//	    Booking booking = bookingRepo.findById(bookingId)
//	            .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
//
//	    // Check if cancellation is allowed (1 day before departure)
////	    if (booking.getFlight().getDepartureTime().isBefore(LocalDateTime.now().plusDays(1))) {
////	        throw new RuntimeException("Cancellation not allowed within 24 hours of departure");
////	    }
//
//	    // Create refund request
//	    RefundDto refundDto = new RefundDto(
//	            booking.getTotalAmount(),
//	            LocalDateTime.now(),
//	            "BANK_TRANSFER", // or set default
//	            "TXN-" + UUID.randomUUID(),
//	            "User cancelled booking",
//	            "PENDING",
//	            booking.getPassenger().getUserId(),
//	            booking.getBookingId()
//	    );
//	    refundService.addRefund(refundDto);
//
//	    bookingRepo.delete(booking);
//	    return "Booking cancelled, refund initiated.";
//	}
	
	public String cancelBooking(int bookingId) throws BookingNotFoundException {
	    Booking booking = bookingRepo.findById(bookingId)
	            .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

	    // Mark as cancelled
	    booking.setStatus("CANCELLED");
	    bookingRepo.save(booking);

	    // Create refund request
	    RefundDto refundDto = new RefundDto(
	            booking.getTotalAmount(),
	            LocalDateTime.now(),
	            "BANK_TRANSFER", // default
	            "TXN-" + UUID.randomUUID(),
	            "User cancelled booking",
	            "PENDING",
	            booking.getPassenger().getUserId(),
	            booking.getBookingId()
	    );
	    refundService.addRefund(refundDto);

	    return "Booking cancelled, refund initiated.";
	}

}
