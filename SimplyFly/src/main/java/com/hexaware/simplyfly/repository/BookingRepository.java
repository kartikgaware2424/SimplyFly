package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findByPassengerUserId(int userId);


	List<Booking> findByFlightFlightId(int flightId);

	List<Booking> findByStatus(String status);
}
