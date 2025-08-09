package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entity.Route;

public interface RouteService {
	Route getRouteById(int id);

	List<Route> searchRoutes(String origin, String destination);

	Route addRoute(Route route);
}
