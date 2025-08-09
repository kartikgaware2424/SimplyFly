package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entity.Refund;
import com.hexaware.simplyfly.repository.RefundRepository;

@Service
public class RefundServiceImpl implements RefundService {
	@Autowired
	private RefundRepository refundRepo;

	public Refund getRefundById(Long id) {
		return refundRepo.findById(id).orElse(null);
	}

	public List<Refund> getRefundsByUser(Long userId) {
		return refundRepo.findByUserUserId(userId);
	}

	public Refund getRefundByBooking(Long bookingId) {
		return refundRepo.findByBookingBookingId(bookingId);
	}

	public Refund addRefund(Refund refund) {
		return refundRepo.save(refund);
	}
}
