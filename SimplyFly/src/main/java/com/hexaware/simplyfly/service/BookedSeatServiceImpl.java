package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entity.BookedSeat;
import com.hexaware.simplyfly.repository.BookedSeatRepository;

@Service
public class BookedSeatServiceImpl implements BookedSeatService {
    @Autowired
    private BookedSeatRepository bookedSeatRepo;

    public List<BookedSeat> getBookedSeatsByBooking(Long bookingId) {
        return bookedSeatRepo.findByBookingBookingId(bookingId);
    }

    public List<BookedSeat> getBookedSeatsByPassenger(Long passengerId) {
        return bookedSeatRepo.findByPassengerUserId(passengerId);
    }

    public BookedSeat addBookedSeat(BookedSeat bookedSeat) {
        return bookedSeatRepo.save(bookedSeat);
    }
}
