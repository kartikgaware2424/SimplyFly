package com.hexaware.simplyfly.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.simplyfly.dto.RefundDto;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Refund;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.exception.RefundNotFoundException;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.RefundRepository;
import com.hexaware.simplyfly.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class RefundServiceImplTest {

	@Autowired
	private RefundService refundService;

	@Autowired
	private RefundRepository refundRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BookingRepository bookingRepo;

	private User user;
	private Booking booking;

	@BeforeEach
	void setUp() {
		refundRepo.deleteAll();
		bookingRepo.deleteAll();
		userRepo.deleteAll();

		user = new User();
		user.setName("Test User");
		user.setEmail("testuser@example.com");
		user.setPassword("password123");
		user = userRepo.save(user);

		// Create a booking
		booking = new Booking();
		booking.setBookingDate(LocalDateTime.now());
		booking.setPassenger(user);
		booking.setTotalAmount(5000.0);
		booking = bookingRepo.save(booking);
	}

	@Test
	void addRefund_shouldAddRefundSuccessfully() {
		RefundDto refundDto = new RefundDto(5000.0, LocalDateTime.now(), "UPI", "TXN12345", "Flight cancelled",
				"PENDING", user.getUserId(), booking.getBookingId());

		Refund savedRefund = refundService.addRefund(refundDto);

		assertThat(savedRefund).isNotNull();

	}

	@Test
	void getRefundById_shouldReturnRefund() throws RefundNotFoundException {
		RefundDto refundDto = new RefundDto(5000.0, LocalDateTime.now(), "UPI", "TXN12346", "Flight cancelled",
				"PENDING", user.getUserId(), booking.getBookingId());
		Refund savedRefund = refundService.addRefund(refundDto);

		Refund foundRefund = refundService.getRefundById(savedRefund.getRefundId());
		assertEquals(savedRefund.getRefundId(), foundRefund.getRefundId());
	}

	@Test
	void getRefundsByUser_shouldReturnRefunds() throws RefundNotFoundException {
		RefundDto refundDto1 = new RefundDto(3000.0, LocalDateTime.now(), "UPI", "TXN100", "Flight cancelled",
				"PENDING", user.getUserId(), booking.getBookingId());

		refundService.addRefund(refundDto1);

		List<Refund> refunds = refundService.getRefundsByUser(user.getUserId());
		assertEquals(1, refunds.size());
	}

	@Test
	void getRefundByBooking_shouldReturnRefund() throws RefundNotFoundException {
		RefundDto refundDto = new RefundDto(3000.0, LocalDateTime.now(), "UPI", "TXN101", "Flight cancelled", "PENDING",
				user.getUserId(), booking.getBookingId());

		Refund savedRefund = refundService.addRefund(refundDto);

		Refund refundByBooking = refundService.getRefundByBooking(booking.getBookingId());
		assertEquals(savedRefund.getRefundId(), refundByBooking.getRefundId());
	}

	@Test
	void getRefundById_shouldThrowException_whenRefundNotFound() {
		assertThrows(RefundNotFoundException.class, () -> refundService.getRefundById(999));
	}
}