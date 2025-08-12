package com.hexaware.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.RefundDto;
import com.hexaware.simplyfly.entity.Refund;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.RefundNotFoundException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.service.RefundService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/refunds")
public class RefundController {

	@Autowired
	private RefundService refundService;

	// Add new refund
	@PostMapping("/add")
	public Refund addRefund(@Valid @RequestBody RefundDto refundDto)
			throws UserNotFoundException, BookingNotFoundException {
		return refundService.addRefund(refundDto);
	}

	// Get refund by ID
	@GetMapping("/{id}")
	public Refund getRefundById(@PathVariable int id) throws RefundNotFoundException {
		return refundService.getRefundById(id);
	}

	// Get refunds for a specific user
	@GetMapping("/user/{userId}")
	public List<Refund> getRefundsByUser(@PathVariable int userId) throws RefundNotFoundException {
		return refundService.getRefundsByUser(userId);
	}

	// Get refund by booking ID
	@GetMapping("/booking/{bookingId}")
	public Refund getRefundByBooking(@PathVariable int bookingId) throws RefundNotFoundException {
		return refundService.getRefundByBooking(bookingId);
	}

}