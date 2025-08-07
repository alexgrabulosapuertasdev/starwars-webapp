package com.starwarsbackend.starwarsbackend.planet.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starwarsbackend.starwarsbackend.planet.model.PlanetPaginatedResponse;

@RequestMapping("/planet")
public interface PlanetApi {
  @GetMapping
  ResponseEntity<PlanetPaginatedResponse> findPlanets(
      @RequestParam(defaultValue = "") String search,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "name") String orderBy,
      @RequestParam(defaultValue = "asc") String orderDirection);
}
