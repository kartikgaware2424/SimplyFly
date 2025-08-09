package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.simplyfly.entity.User;

public interface UserService {
	User addUser(User user);

	User getUserById(int id);

	List<User> getAllUsers();

	List<User> getUsersByRole(String role);

	Optional<User> getUserByEmail(String email);

}
