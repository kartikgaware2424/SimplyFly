package com.hexaware.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.SeatDto;
import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.exception.SeatNotAvailableException;
import com.hexaware.simplyfly.service.SeatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable int id) throws SeatNotAvailableException {
        return seatService.getSeatById(id);
    }

    @GetMapping("/flight/{flightId}")
    public List<Seat> getSeatsByFlight(@PathVariable int flightId) throws SeatNotAvailableException {
        return seatService.getSeatsByFlight(flightId);
    }

    @GetMapping("/status/{isBooked}")
    public List<Seat> getSeatsByBookingStatus(@PathVariable boolean isBooked) throws SeatNotAvailableException {
        return seatService.getSeatsByBookingStatus(isBooked);
    }

    @GetMapping("/flight/name/{flightName}")
    public List<Seat> getSeatsByFlightName(@PathVariable String flightName) throws SeatNotAvailableException {
        return seatService.getSeatsByFlightName(flightName);
    }

    @PostMapping
    public Seat addSeat(@Valid @RequestBody SeatDto seatDto) {
        return seatService.addSeat(seatDto);
    }
}
