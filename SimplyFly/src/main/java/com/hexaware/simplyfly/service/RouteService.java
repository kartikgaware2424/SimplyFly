package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.RouteDto;
import com.hexaware.simplyfly.entity.Route;
import com.hexaware.simplyfly.exception.RouteNotFoundException;

public interface RouteService {
	Route addRoute(RouteDto routeDto);

	List<Route> searchRoutes(String origin, String destination);
	
	List<Route> getAllRoutes();

	Route updateRoute(int routeId, RouteDto routeDto) throws RouteNotFoundException;

	void deleteRoute(int routeId) throws RouteNotFoundException;

}
