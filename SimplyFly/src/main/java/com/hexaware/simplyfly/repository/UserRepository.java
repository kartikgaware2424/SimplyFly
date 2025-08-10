package com.hexaware.simplyfly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email); //find user by email
    List<User> findByRole(String role); // for fetching all Passenger, Owners, etc.
}
