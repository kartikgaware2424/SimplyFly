package com.hexaware.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.PaymentDto;
import com.hexaware.simplyfly.entity.Payment;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.PaymentFailedException;
import com.hexaware.simplyfly.service.PaymentService;

import jakarta.validation.Valid;
/**
 * This class handles payment Controller 
 * Features:
 * add payments
 * get payment by ID
 * get payment by status
 * get payment by booking 
 * 
 * @author Kartik Gaware
 * 
 * 
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
	public Payment addPayment(@Valid @RequestBody PaymentDto paymentDto) throws BookingNotFoundException {
		return paymentService.addPayment(paymentDto);
	}

	@GetMapping("/getById/{id}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public Payment getPaymentById(@PathVariable int id) {
		return paymentService.getPaymentById(id);
	}

	@GetMapping("/getBystatus/{status}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public List<Payment> getPaymentsByStatus(@PathVariable String status) {
		return paymentService.getPaymentsByStatus(status);
	}

	@GetMapping("/getBybooking/{bookingId}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public Payment getPaymentByBooking(@PathVariable int bookingId) throws PaymentFailedException {
		return paymentService.getPaymentByBooking(bookingId);
	}

}