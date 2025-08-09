package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.BookedSeat;

@Repository
public interface BookedSeatRepository extends JpaRepository<BookedSeat, Long> {
	List<BookedSeat> findByBookingBookingId(Long bookingId);

	List<BookedSeat> findByPassengerUserId(Long passengerId);
}
