package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Refund;

public interface RefundService {
	Refund getRefundById(Long id);

	List<Refund> getRefundsByUser(Long userId);

	Refund getRefundByBooking(Long bookingId);

	Refund addRefund(Refund refund);
}
