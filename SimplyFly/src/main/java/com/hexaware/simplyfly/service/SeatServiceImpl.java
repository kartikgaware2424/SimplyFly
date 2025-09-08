package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.SeatDto;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.SeatNotAvailableException;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.SeatRepository;
/**
 * Seat Service Implementation 
 * Add seat
 * Get seat By Id
 * Get seat By Flight
 * Get seat By Status(True/False)
 * Get seat by flight Name
 * @author Kartik Gaware
 */
@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepo;

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	private BookingRepository bookingRepo;
	
	@Override
	public Seat addSeat(SeatDto seatDto) throws FlightNotFoundException, BookingNotFoundException {
		Seat seat = new Seat();
		seat.setSeatNumber(seatDto.getSeatNumber());
		seat.setBooked(seatDto.isBooked());
		seat.setSeatClass(seatDto.getSeatClass());

		Flight flight = flightRepo.findById(seatDto.getFlightId()).orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + seatDto.getFlightId()));
	    seat.setFlight(flight);

	    
	    if (seatDto.getBookingId() > 0) {
	        Booking booking = bookingRepo.findById(seatDto.getBookingId()).orElse(null);
	                
	        seat.setBooking(booking);
	    }

		return seatRepo.save(seat);
	}

	@Override
	public Seat getSeatById(int id) throws SeatNotAvailableException {
		return seatRepo.findById(id).orElseThrow(() -> new SeatNotAvailableException("Seat not found with id: " + id));
	}

	@Override
	public List<Seat> getSeatsByFlight(int flightId) throws SeatNotAvailableException {
		List<Seat> seats = seatRepo.findByFlightFlightId(flightId);
		if (seats == null || seats.isEmpty()) {
			throw new SeatNotAvailableException("No seats found for flight with id: " + flightId);
		}
		return seats;
	}

	@Override
	public List<Seat> getSeatsByBookingStatus(boolean isBooked) throws SeatNotAvailableException {
		List<Seat> seats = seatRepo.findByIsBooked(isBooked);
		if (seats == null || seats.isEmpty()) {
			throw new SeatNotAvailableException("No seats found with booking status: " + isBooked);
		}
		return seats;
	}

	

	@Override
	public List<Seat> getSeatsByFlightName(String flightName) throws SeatNotAvailableException {
		List<Seat> seats = seatRepo.findByFlightFlightName(flightName);
		if (seats == null || seats.isEmpty()) {
			throw new SeatNotAvailableException("No seats found for flight with name: " + flightName);
		}
		return seats;
	}

	 @Override
	    public List<Seat> getSeatsBySeatNumber(String seatNumber) throws SeatNotAvailableException {
	        List<Seat> seats = seatRepo.findBySeatNumber(seatNumber);
	        if (seats.isEmpty()) throw new SeatNotAvailableException("Seat not available: " + seatNumber);
	        return seats;
	    }
}
