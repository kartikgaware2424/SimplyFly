package com.hexaware.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.RouteDto;
import com.hexaware.simplyfly.entity.Route;
import com.hexaware.simplyfly.exception.RouteNotFoundException;
import com.hexaware.simplyfly.service.RouteService;

import jakarta.validation.Valid;
/**
 * This class handles Route Controller 
 * Features:
 * add route
 * get route by origin and destination
 * update route
 * get delete
 * 
 * @author Kartik Gaware
 * 
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/routes")
public class RouteController {

	@Autowired
	private RouteService routeService;

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public Route addRoute(@Valid @RequestBody RouteDto routeDto) {
		return routeService.addRoute(routeDto);
	}

	@GetMapping("/getByOriginDestination/{origin}/{destination}")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public List<Route> searchRoutes(@PathVariable String origin, @PathVariable String destination) {
		return routeService.searchRoutes(origin, destination);
	}
	
	@GetMapping("/getAllRoutes")
	@PreAuthorize("hasAnyRole('PASSENGER','OWNER','ADMIN')")
	public List<Route>getAllRoutes() {
		return routeService.getAllRoutes();
	}

	@PutMapping("/updateById/{id}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public Route updateRoute(@PathVariable int id, @Valid @RequestBody RouteDto updatedRoute)
			throws RouteNotFoundException {
		return routeService.updateRoute(id, updatedRoute);
	}

	@DeleteMapping("/deleteById/{id}")
	@PreAuthorize("hasAnyRole('OWNER','ADMIN')")
	public String deleteRoute(@PathVariable int id) throws RouteNotFoundException {
		routeService.deleteRoute(id);
		return "Route deleted successfully with ID: " + id;
	}
	
	
}