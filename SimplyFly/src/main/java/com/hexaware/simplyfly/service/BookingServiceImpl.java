package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepo;

    public Booking getBookingById(int id) {
        return bookingRepo.findById(id).orElse(null);
    }

    public List<Booking> getBookingsByUser(int userId) {
        return bookingRepo.findByUserUserId(userId);
    }

    public List<Booking> getBookingsByFlight(int flightId) {
        return bookingRepo.findByFlightFlightId(flightId);
    }

    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepo.findByStatus(status);
    }

    public Booking addBooking(Booking booking) {
        return bookingRepo.save(booking);
    }
}

