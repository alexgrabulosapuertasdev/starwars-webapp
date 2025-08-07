package com.starwarsbackend.starwarsbackend.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.starwarsbackend.starwarsbackend.planet.api.PlanetApi;
import com.starwarsbackend.starwarsbackend.planet.model.PlanetPaginatedResponse;
import com.starwarsbackend.starwarsbackend.planet.service.PlanetService;

@RestController
public class PlanetController implements PlanetApi {
  @Autowired
  private PlanetService planetService;

  @Override
  public ResponseEntity<PlanetPaginatedResponse> findPlanets(String search, int page, String orderBy,
      String orderDirection) {
    return ResponseEntity.ok(planetService.findPlanets(search, page, orderBy, orderDirection));
  }
}
