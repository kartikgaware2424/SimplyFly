package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.UserDto;
import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.entity.UserRole;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public User addUser(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
        user.setRole(UserRole.valueOf(userDto.getRole().name()));// convert the enum to string
		user.setContactNumber(userDto.getContactNumber());
		user.setGender(userDto.getGender());
		user.setAge(userDto.getAge()); 
		user.setAddress(userDto.getAddress());

		return userRepo.save(user);
	}

	@Override
	public User getUserById(int id) {
		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<User> getUsersByRole(String role) {
		return userRepo.findByRole(role);
	}

	@Override
	public List<User> getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public String deleteUser(int id) {
		
		 userRepo.deleteById(id);;
		 return "User Deleted";
	}
}
