package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entity.Payment;
import com.hexaware.simplyfly.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepo;

	public Payment getPaymentById(int id) {
		return paymentRepo.findById(id).orElse(null);
	}

	public List<Payment> getPaymentsByStatus(String status) {
		return paymentRepo.findByStatus(status);
	}

	public Payment getPaymentByBooking(int bookingId) {
		return paymentRepo.findByBookingBookingId(bookingId);
	}

	public Payment addPayment(Payment payment) {
		return paymentRepo.save(payment);
	}
}
