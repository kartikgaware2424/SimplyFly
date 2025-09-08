package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.RefundDto;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Refund;
import com.hexaware.simplyfly.entity.RefundStatus;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.exception.RefundNotFoundException;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.RefundRepository;
import com.hexaware.simplyfly.repository.UserRepository;
/**
 * Refund service implementation
 * Logic:
 * Add refund
 * Get refund by ID
 * Get refund by user
 * Get refund by booking
 * @author Kartik Gaware
 */
@Service
public class RefundServiceImpl implements RefundService {
	@Autowired
	private RefundRepository refundRepo;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BookingRepository bookingRepo;
	
	@Override
	public Refund addRefund(RefundDto refundDto) {
		Refund refund = new Refund();
		refund.setAmount(refundDto.getAmount());
		refund.setRefundDate(refundDto.getRefundDate());
		refund.setRefundMethod(refundDto.getRefundMethod());
		refund.setTransactionId(refundDto.getTransactionId());
		refund.setReason(refundDto.getReason());
		refund.setStatus(RefundStatus.valueOf(refundDto.getStatus()));

		User user = userRepo.findById(refundDto.getUserId()).orElseThrow();
		Booking booking = bookingRepo.findById(refundDto.getBookingId()).orElseThrow();

		refund.setUser(user);
		refund.setBooking(booking);

		return refundRepo.save(refund);
	}

	@Override
	public Refund getRefundById(int id) throws RefundNotFoundException {
		return refundRepo.findById(id).orElseThrow(() -> new RefundNotFoundException("Refund not found with id: " + id));
	}

	@Override
	public List<Refund> getRefundsByUser(int userId) throws RefundNotFoundException {
		List<Refund> refunds = refundRepo.findByUserUserId(userId);
		if (refunds == null || refunds.isEmpty()) {
			throw new RefundNotFoundException("No refunds found for user with id: " + userId);
		}
		return refunds;
	}

	@Override
	public Refund getRefundByBooking(int bookingId) throws RefundNotFoundException {
		Refund refund = refundRepo.findByBookingBookingId(bookingId);
		if (refund == null) {
			throw new RefundNotFoundException("Refund not found for booking id: " + bookingId);
		}
		return refund;
	}
	
	// RefundService.java

	@Override
	public Refund updateRefundStatus(int refundId, RefundStatus status) throws RefundNotFoundException {
	    Refund refund = refundRepo.findById(refundId)
	            .orElseThrow(() -> new RefundNotFoundException("Refund not found"));
	    refund.setStatus(status);
	    return refundRepo.save(refund);
	}

	@Override
	public List<Refund> getRefundsByOwner(int ownerId) throws RefundNotFoundException {
		 List<Refund> refunds = refundRepo.findByBooking_Flight_Owner_UserId(ownerId);
		    if (refunds.isEmpty()) {
		        throw new RefundNotFoundException("No refunds found for owner with ID: " + ownerId);
		    }
		    return refunds;
	}
	
	



	
	



	

}
