package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
	List<Refund> findByUserUserId(Long userId);

	Refund findByBookingBookingId(Long bookingId);
}
