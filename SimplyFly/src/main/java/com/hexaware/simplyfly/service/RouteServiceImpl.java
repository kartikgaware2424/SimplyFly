package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.RouteDto;
import com.hexaware.simplyfly.entity.Route;
import com.hexaware.simplyfly.exception.RouteNotFoundException;
import com.hexaware.simplyfly.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {
	@Autowired
	private RouteRepository routeRepo;

	 @Override
	    public Route addRoute(RouteDto routeDto) {
	        Route route = new Route();
	        route.setOrigin(routeDto.getOrigin());
	        route.setDestination(routeDto.getDestination());
	        route.setDistanceInKm(routeDto.getDistanceInKm());
	        route.setTravelDuration(routeDto.getTravelDuration());

	        return routeRepo.save(route);
	    }
	@Override
	public List<Route> searchRoutes(String origin, String destination) {
		return routeRepo.findByOriginAndDestination(origin, destination);
	}
	
	@Override
	public Route updateRoute(int routeId, RouteDto routeDto) throws RouteNotFoundException {
	    Route existingRoute = routeRepo.findById(routeId)
	            .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + routeId));

	    existingRoute.setOrigin(routeDto.getOrigin());
	    existingRoute.setDestination(routeDto.getDestination());
	    existingRoute.setDistanceInKm(routeDto.getDistanceInKm());
	    existingRoute.setTravelDuration(routeDto.getTravelDuration());

	    return routeRepo.save(existingRoute);
	}

	@Override
	public void deleteRoute(int routeId) throws RouteNotFoundException {
	    Route existingRoute = routeRepo.findById(routeId)
	            .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + routeId));

	    routeRepo.delete(existingRoute);
	}

	
}
