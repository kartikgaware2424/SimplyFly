package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Payment;

public interface PaymentService {
	Payment getPaymentById(Long id);

	List<Payment> getPaymentsByStatus(String status);

	Payment getPaymentByBooking(Long bookingId);

	Payment addPayment(Payment payment);
}
