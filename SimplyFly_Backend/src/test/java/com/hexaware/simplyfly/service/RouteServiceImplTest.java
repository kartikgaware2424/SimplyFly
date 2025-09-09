package com.hexaware.simplyfly.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.simplyfly.dto.RouteDto;
import com.hexaware.simplyfly.entity.Route;
import com.hexaware.simplyfly.exception.RouteNotFoundException;
import com.hexaware.simplyfly.repository.RouteRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional 
class RouteServiceImplTest {

	@Autowired
	private RouteService routeService;

	@Autowired
	private RouteRepository routeRepo;

	@BeforeEach
	void setUp() {
		
		routeRepo.findAll().forEach(routeRepo::delete);
	}

	@Test
	void addRoute_shouldAddRouteSuccessfully() {
		RouteDto routeDto = new RouteDto("Mumbai","Delhi",1400.0,"2h 15m");
		Route savedRoute = routeService.addRoute(routeDto);
        assertThat(savedRoute).isNotNull();

	}

	@Test
	void updateRoute_shouldUpdateExistingRoute() throws RouteNotFoundException {
		RouteDto routeDto = new RouteDto("Mumbai", "Delhi", 1400.0, "2h 15m");
		Route savedRoute = routeService.addRoute(routeDto);
		RouteDto updateDto = new RouteDto("Mumbai", "Delhi", 1500.0, "2h 30m");
		Route updatedRoute = routeService.updateRoute(savedRoute.getRouteId(), updateDto);
		assertEquals(1500.0, updatedRoute.getDistanceInKm());
		
	}

	@Test
	void updateRoute_shouldThrowException_whenRouteNotFound() {
		RouteDto updateDto = new RouteDto("Mumbai", "Delhi", 1500.0, "2h 30m");
		assertThrows(RouteNotFoundException.class, () -> {
			routeService.updateRoute(999, updateDto);});
	}

	@Test
	void deleteRoute_shouldDeleteExistingRoute() throws RouteNotFoundException {
		RouteDto routeDto = new RouteDto("Mumbai", "Delhi", 1400.0, "2h 15m");
		Route savedRoute = routeService.addRoute(routeDto);
		routeService.deleteRoute(savedRoute.getRouteId());
		assertThrows(RouteNotFoundException.class, () -> routeService.deleteRoute(savedRoute.getRouteId()));
	}

	@Test
	void deleteRoute_shouldThrowException_whenRouteNotFound() {
		assertThrows(RouteNotFoundException.class, () -> routeService.deleteRoute(999));
	}
}
