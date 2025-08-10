package com.hexaware.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/bookedSeats")
public class BookedSeatController {

    @Autowired
    private BookedSeatService bookedSeatService;

    @GetMapping("/booking/{bookingId}")
    public List<BookedSeat> getBookedSeatsByBooking(@PathVariable int bookingId) {
        return bookedSeatService.getBookedSeatsByBooking(bookingId);
    }

    @GetMapping("/passenger/{passengerId}")
    public List<BookedSeat> getBookedSeatsByPassenger(@PathVariable int passengerId) {
        return bookedSeatService.getBookedSeatsByPassenger(passengerId);
    }

    @PostMapping
    public BookedSeat addBookedSeat(@Valid @RequestBody BookedSeatDto bookedSeatDto) {
        return bookedSeatService.addBookedSeatFromDto(bookedSeatDto);
    }
}
