package com.hexaware.simplyfly.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.simplyfly.entity.BookedSeat;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.repository.BookedSeatRepository;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.SeatRepository;
import com.hexaware.simplyfly.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional 
class BookedSeatServiceImplTest {

    @Autowired
    private BookedSeatService bookedSeatService;

    @Autowired
    private BookedSeatRepository bookedSeatRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private UserRepository userRepo;

    private User passenger;
    private Booking booking;
    private Seat seat;

    @BeforeEach
    void setUp() {
        bookedSeatRepo.deleteAll();
        bookingRepo.deleteAll();
        seatRepo.deleteAll();
        userRepo.deleteAll();

        passenger = new User();
        passenger.setName("Passenger 1");
        passenger.setEmail("passenger1@example.com");
        passenger.setPassword("pass123");
        passenger = userRepo.save(passenger);

     
        booking = new Booking();
        booking.setPassenger(passenger);
        booking = bookingRepo.save(booking);

        seat = new Seat();
        seat.setSeatNumber("1A");
        seat.setSeatClass("Economy");
        seat.setBooked(false);
        seat = seatRepo.save(seat);

        
        BookedSeat bookedSeat = new BookedSeat();
        bookedSeat.setBooking(booking);
        bookedSeat.setSeat(seat);
        bookedSeat.setPassenger(passenger);
        bookedSeat.setPrice(5000.0);
        bookedSeatRepo.save(bookedSeat);
    }

    @Test
    void getBookedSeatsByBooking_shouldReturnSeats() {
        List<BookedSeat> result = bookedSeatService.getBookedSeatsByBooking(booking.getBookingId());
        assertThat(result).isNotEmpty();
     
    }

    @Test
    void getBookedSeatsByPassenger_shouldReturnSeats() {
        List<BookedSeat> result = bookedSeatService.getBookedSeatsByPassenger(passenger.getUserId());
        assertThat(result).isNotEmpty();
       
    }
}
