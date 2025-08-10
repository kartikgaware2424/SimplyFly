package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.RouteDto;
import com.hexaware.simplyfly.entity.Route;
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

	
}
