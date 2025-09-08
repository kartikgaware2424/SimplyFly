package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findByStatus(String status);

	Payment findByBookingBookingId(int bookingId);
}
