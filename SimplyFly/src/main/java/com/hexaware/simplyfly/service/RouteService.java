package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.RouteDto;
import com.hexaware.simplyfly.entity.Route;

public interface RouteService {
	Route addRoute(RouteDto routeDto);

	List<Route> searchRoutes(String origin, String destination);

	
}
