package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Integer> {
	List<Refund> findByUserUserId(int userId);

	Refund findByBookingBookingId(int bookingId);
	List<Refund> findByBooking_Flight_Owner_UserId(int ownerId);
}
