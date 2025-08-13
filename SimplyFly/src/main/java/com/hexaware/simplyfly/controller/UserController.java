package com.hexaware.simplyfly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.UserDto;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public User addUser(@Valid @RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}

	@GetMapping("getUserById/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','OWNER')")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/getUserByRole/{role}")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUsersByRole(@PathVariable String role) {
		return userService.getUsersByRole(role);
	}

	@GetMapping("/getUserByEmail/{email}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Optional<User> getUserByEmail(@PathVariable String email) {
		return userService.getUserByEmail(email);
	}

	@DeleteMapping("/deleteById/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return "User deleted Successfully ";
	}
}