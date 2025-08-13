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

import com.hexaware.simplyfly.dto.PaymentDto;
import com.hexaware.simplyfly.entity.Booking;
import com.hexaware.simplyfly.entity.Payment;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.PaymentFailedException;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.PaymentRepository;
import com.hexaware.simplyfly.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional 
class PaymentServiceImplTest {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private UserRepository userRepo;

	private User user;
	private Booking booking;

	@BeforeEach
	void setUp() {
	
		paymentRepo.deleteAll();
		bookingRepo.deleteAll();
		userRepo.deleteAll();

		
		user = new User();
		user.setName("Test User");
		user.setEmail("test@example.com");
		user.setPassword("password");
		user = userRepo.save(user);

		
		booking = new Booking();
		booking.setBookingDate(LocalDateTime.now());
		booking.setTotalAmount(5000.0);
		booking.setPassenger(user);
		booking = bookingRepo.save(booking);
	}

	@Test
	void addPayment_shouldSavePaymentSuccessfully() throws BookingNotFoundException {
		PaymentDto dto = new PaymentDto(5000.0, LocalDateTime.now(), "UPI", "TXN12345", "SUCCESS",
				booking.getBookingId());

		Payment savedPayment = paymentService.addPayment(dto);

		assertThat(savedPayment).isNotNull();
	}

	@Test
	void addPayment_shouldThrowExceptionWhenBookingNotFound() {
		PaymentDto dto = new PaymentDto(4000.0, LocalDateTime.now(), "UPI", "TXN99999", "FAILED", 999);

		assertThrows(BookingNotFoundException.class, () -> paymentService.addPayment(dto));
	}

	@Test
	void getPaymentById_shouldReturnPayment() throws BookingNotFoundException {
		PaymentDto dto = new PaymentDto(3000.0, LocalDateTime.now(), "CARD", "TXN100", "SUCCESS",
				booking.getBookingId());
		Payment saved = paymentService.addPayment(dto);

		Payment found = paymentService.getPaymentById(saved.getPaymentId().intValue());
		assertThat(found).isNotNull();
		
	}

	@Test
	void getPaymentByBooking_shouldReturnPayment() throws BookingNotFoundException {
		PaymentDto dto = new PaymentDto(3500.0, LocalDateTime.now(), "UPI", "TXN102", "SUCCESS",
				booking.getBookingId());
		Payment saved = paymentService.addPayment(dto);

		Payment found = paymentService.getPaymentByBooking(booking.getBookingId());
		assertEquals(saved.getPaymentId(), found.getPaymentId());
	}

}