package com.hexaware.simplyfly.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.simplyfly.dto.UserDto;
import com.hexaware.simplyfly.dto.UserRoleDto;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.entity.UserRole;
import com.hexaware.simplyfly.repository.UserRepository;

@SpringBootTest
@Transactional

class UserServiceImplTest {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserServiceImpl userService;

	private UserDto userDto;

	@BeforeEach
	void setUp() {

		userRepo.deleteAll();

		userDto = new UserDto("Kartik Gaware", "kartik@example.com", "password123", 25, UserRoleDto.PASSENGER,
				"9876543210", "Male", "123 Street, City");
	}

	@Test
	void addUser_shouldAddUserSuccessfully() {
		User savedUser = userService.addUser(userDto);
		assertNotNull(savedUser.getUserId(), "User ID should not be null");
		
	}

	@Test
	void getUserById_shouldReturnUser() {
		User savedUser = userService.addUser(userDto);
		User foundUser = userService.getUserById(savedUser.getUserId());
		assertNotNull(foundUser);
		
	}

	@Test
	void getAllUsers_shouldReturnAllUsers() {
		userService.addUser(userDto);
		List<User> users = userService.getAllUsers();
		assertEquals(1, users.size(), "There should be 1 user");
	}

	@Test
	void getUserByEmail_shouldReturnUser() {
		userService.addUser(userDto);
		Optional<User> user = userService.getUserByEmail("kartik@example.com");
		assertTrue(user.isPresent(), "User should be found by email");
	
	}

	@Test
	void deleteUser_shouldDeleteUser() {
		User savedUser = userService.addUser(userDto);

		String response = userService.deleteUser(savedUser.getUserId());
		assertEquals("User Deleted", response);

		
		Optional<User> deletedUser = userService.getUserByEmail(userDto.getEmail());
		assertTrue(deletedUser.isEmpty(), "Deleted user should not exist");
	}
}
