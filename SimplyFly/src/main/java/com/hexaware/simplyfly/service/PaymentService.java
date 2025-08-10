package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.PaymentDto;
import com.hexaware.simplyfly.entity.Payment;
import com.hexaware.simplyfly.exception.BookingNotFoundException;

public interface PaymentService {
	Payment getPaymentById(int id);

	List<Payment> getPaymentsByStatus(String status);

	Payment getPaymentByBooking(int bookingId);

	 Payment addPayment(PaymentDto paymentDto) throws BookingNotFoundException;
}
