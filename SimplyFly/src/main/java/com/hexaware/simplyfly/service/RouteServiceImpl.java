package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entity.Route;
import com.hexaware.simplyfly.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {
	@Autowired
	private RouteRepository routeRepo;

	public Route getRouteById(Long id) {
		return routeRepo.findById(id).orElse(null);
	}

	public List<Route> searchRoutes(String origin, String destination) {
		return routeRepo.findByOriginAndDestination(origin, destination);
	}

	public Route addRoute(Route route) {
		return routeRepo.save(route);
	}
}
