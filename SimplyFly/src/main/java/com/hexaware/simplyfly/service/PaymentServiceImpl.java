package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.PaymentDto;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Payment;
import com.hexaware.simplyfly.entity.PaymentMethod;
import com.hexaware.simplyfly.entity.PaymentStatus;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.PaymentFailedException;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public Payment getPaymentById(int id) {
		return paymentRepo.findById(id).orElse(null);
	}

	@Override
	public List<Payment> getPaymentsByStatus(String status) {
		return paymentRepo.findByStatus(status);
	}

	@Override
	public Payment getPaymentByBooking(int bookingId) {
		Payment payment = paymentRepo.findByBookingBookingId(bookingId);
		if (payment == null) {
			throw new PaymentFailedException("Payment not found for booking ID " + bookingId);
		}
		return payment;
	}

	@Override
	public Payment addPayment(PaymentDto dto) throws BookingNotFoundException {
		Booking booking = bookingRepo.findById(dto.getBookingId())
				.orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + dto.getBookingId()));

		Payment payment = new Payment();
		payment.setAmount(dto.getAmount());
		payment.setPaymentDate(dto.getPaymentDate());
		payment.setPaymentMethod(PaymentMethod.valueOf(dto.getPaymentMethod().toUpperCase()));
		payment.setTransactionId(dto.getTransactionId());
		payment.setStatus(PaymentStatus.valueOf(dto.getStatus().toUpperCase()));
		payment.setBooking(booking);

		return paymentRepo.save(payment);
	}
}
