package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.simplyfly.dto.UserDto;
import com.hexaware.simplyfly.entity.User;

public interface UserService {

	User addUser(UserDto userDto);

	User getUserById(int id);

	List<User> getAllUsers();

	List<User> getUsersByRole(String role);

	List<User> getUserByEmail(String email);
	String deleteUser(int id);

}
