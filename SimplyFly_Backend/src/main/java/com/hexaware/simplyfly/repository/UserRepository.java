package com.hexaware.simplyfly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.User;
import com.hexaware.simplyfly.entity.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);//find user by email
    List<User> findByRole(UserRole  role); // for fetching all Passenger, Owners, etc.
}
