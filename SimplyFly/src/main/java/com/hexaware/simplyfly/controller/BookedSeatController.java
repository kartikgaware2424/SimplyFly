package com.hexaware.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.BookedSeatDto;
import com.hexaware.simplyfly.entity.BookedSeat;
import com.hexaware.simplyfly.service.BookedSeatService;

import jakarta.validation.Valid;
/**
 * This class handles bookedSeat Controller 
 * Features:
 * getByBooking
 * getByPassangerId
 * @author Kartik Gaware
 * 
 */
@RestController
@RequestMapping("/api/bookedSeats")
public class BookedSeatController {

    @Autowired
    private BookedSeatService bookedSeatService;
    
   
    
    @GetMapping("/getByBookings/{bookingId}")
    @PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
    public List<BookedSeat> getBookedSeatsByBooking(@PathVariable int bookingId) {
        return bookedSeatService.getBookedSeatsByBooking(bookingId);
    }
   
    @GetMapping("/getBypassenger/{passengerId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<BookedSeat> getBookedSeatsByPassenger(@PathVariable int passengerId) {
        return bookedSeatService.getBookedSeatsByPassenger(passengerId);
    }

   
}
