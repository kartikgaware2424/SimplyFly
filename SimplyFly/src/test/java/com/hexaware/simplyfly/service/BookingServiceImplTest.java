package com.hexaware.simplyfly.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.simplyfly.entity.BookedSeat;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Flight;
import com.hexaware.simplyfly.entity.Route;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.repository.BookedSeatRepository;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.RouteRepository;
import com.hexaware.simplyfly.repository.SeatRepository;
import com.hexaware.simplyfly.repository.UserRepository;

@SpringBootTest
class BookingServiceImplTest {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private BookedSeatRepository bookedSeatRepo;

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private RouteRepository routeRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookingService bookingService;

    private User user;
    private Route route;
    private Flight flight;
    private Seat seat1;
    private Seat seat2;

    @BeforeEach
    void setUp() {
        
        bookedSeatRepo.deleteAll();
        bookingRepo.deleteAll();
        seatRepo.deleteAll();
        flightRepo.deleteAll();
        routeRepo.deleteAll();
        userRepo.deleteAll();

        
        user = new User();
        user.setName("Passenger 1");
        user.setEmail("passenger1@example.com");
        user.setPassword("password");
        user = userRepo.save(user);

        
        route = new Route();
        route.setOrigin("Mumbai");
        route.setDestination("Delhi");
        route.setDistanceInKm(1400);
        route.setTravelDuration("2h 30m");
        route = routeRepo.save(route);

       
        flight = new Flight();
        flight.setFlightName("Flight 101");
        flight.setFlightNumber("F101");
        flight.setTotalSeats(100);
        flight.setFare(5000.0);
        flight.setBaggageCheckIn("20kg");
        flight.setBaggageCabin("7kg");
        flight.setDepartureDate(LocalDate.now().plusDays(1));
        flight.setDepartureTime(LocalDateTime.now().plusDays(1));
        flight.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(2));
        flight.setRoute(route);
        flight.setOwner(user);
        flight = flightRepo.save(flight);

       
        seat1 = new Seat();
        seat1.setSeatNumber("1A");
        seat1.setSeatClass("Economy");
        seat1.setBooked(false);
        seat1.setFlight(flight);
        seat1 = seatRepo.save(seat1);

     
        seat2 = new Seat();
        seat2.setSeatNumber("1B");
        seat2.setSeatClass("Business");
        seat2.setBooked(false);
        seat2.setFlight(flight);
        seat2 = seatRepo.save(seat2);
    }
    @Test
    void testAddBooking() {
        
        Booking booking = new Booking();
        booking.setBookingDate(LocalDateTime.now());
        booking.setFlight(flight);
        booking.setPassenger(user); // passenger
        booking = bookingRepo.save(booking);

        
        BookedSeat bookedSeat = new BookedSeat();
        bookedSeat.setBooking(booking);
        bookedSeat.setPassenger(user); 
        bookedSeat.setSeat(seat1);
        bookedSeat.setPrice(5000.0); 
        bookedSeatRepo.save(bookedSeat);

      
        seat1.setBooked(true);
        seatRepo.save(seat1);

     
        Booking savedBooking = bookingRepo.findById(booking.getBookingId()).orElse(null);
        assertNotNull(savedBooking);
        
    }
}
