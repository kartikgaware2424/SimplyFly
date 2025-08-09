package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Refund;

public interface RefundService {
	Refund getRefundById(int id);

	List<Refund> getRefundsByUser(int userId);

	Refund getRefundByBooking(int bookingId);

	Refund addRefund(Refund refund);
}
