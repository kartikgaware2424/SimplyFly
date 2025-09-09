package com.hexaware.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.RefundDto;
import com.hexaware.simplyfly.entity.Refund;
import com.hexaware.simplyfly.entity.RefundStatus;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.RefundNotFoundException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.service.RefundService;

import jakarta.validation.Valid;
/**
 * This class handles refund Controller 
 * Features:
 * add refund
 * get refund by ID
 * get refund for specific users
 * get refund by booking ID
 * 
 * @author Kartik Gaware
 * 
 */
@RestController
@RequestMapping("/api/refunds")
public class RefundController {

	@Autowired
	private RefundService refundService;

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
	public Refund addRefund(@Valid @RequestBody RefundDto refundDto)
			throws UserNotFoundException, BookingNotFoundException {
		return refundService.addRefund(refundDto);
	}

	@GetMapping("/getById/{id}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public Refund getRefundById(@PathVariable int id) throws RefundNotFoundException {
		return refundService.getRefundById(id);
	}

	@GetMapping("/getByuser/{userId}")
	@PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
	public List<Refund> getRefundsByUser(@PathVariable int userId) throws RefundNotFoundException {
		return refundService.getRefundsByUser(userId);
	}

	
	@GetMapping("/booking/{bookingId}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public Refund getRefundByBooking(@PathVariable int bookingId) throws RefundNotFoundException {
		return refundService.getRefundByBooking(bookingId);
	}
	
	// RefundController.java
	@PutMapping("/approve/{id}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public Refund approveRefund(@PathVariable int id) throws RefundNotFoundException {
	    return refundService.updateRefundStatus(id, RefundStatus.COMPLETED);
	}

	@PutMapping("/reject/{id}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public Refund rejectRefund(@PathVariable int id) throws RefundNotFoundException {
	    return refundService.updateRefundStatus(id, RefundStatus.FAILED);
	}
	
	@GetMapping("/getByOwner/{ownerId}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public List<Refund> getRefundsByOwner(@PathVariable int ownerId) throws RefundNotFoundException {
	    return refundService.getRefundsByOwner(ownerId);
	}


}