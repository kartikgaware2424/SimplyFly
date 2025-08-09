package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
	List<Flight> findByRouteOriginAndRouteDestination(String origin, String destination);

	List<Flight> findByOwnerUserId(int ownerId);
}
