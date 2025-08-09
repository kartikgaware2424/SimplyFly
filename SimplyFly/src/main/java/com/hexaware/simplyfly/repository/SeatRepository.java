package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
	List<Seat> findByFlightFlightId(int flightId);

	List<Seat> findByIsBooked(boolean isBooked);
}
