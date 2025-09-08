package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.RefundDto;
import com.hexaware.simplyfly.entity.Refund;
import com.hexaware.simplyfly.entity.RefundStatus;
import com.hexaware.simplyfly.exception.RefundNotFoundException;

public interface RefundService {
	Refund addRefund(RefundDto refundDto);

	Refund getRefundById(int id) throws RefundNotFoundException;

	List<Refund> getRefundsByUser(int userId) throws RefundNotFoundException;

	Refund getRefundByBooking(int bookingId) throws RefundNotFoundException;

	Refund updateRefundStatus(int refundId, RefundStatus status) throws RefundNotFoundException;
	
    List<Refund> getRefundsByOwner(int ownerId) throws RefundNotFoundException;

}
