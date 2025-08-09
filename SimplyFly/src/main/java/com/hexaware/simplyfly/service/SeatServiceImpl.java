package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entity.Seat;
import com.hexaware.simplyfly.repository.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepo;

    public Seat getSeatById(Long id) {
        return seatRepo.findById(id).orElse(null);
    }

    public List<Seat> getSeatsByFlight(Long flightId) {
        return seatRepo.findByFlightFlightId(flightId);
    }

    public List<Seat> getSeatsByBookingStatus(boolean isBooked) {
        return seatRepo.findByIsBooked(isBooked);
    }

    public Seat addSeat(Seat seat) {
        return seatRepo.save(seat);
    }
}
