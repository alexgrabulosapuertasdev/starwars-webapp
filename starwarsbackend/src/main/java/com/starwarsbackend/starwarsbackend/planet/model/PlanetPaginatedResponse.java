package com.starwarsbackend.starwarsbackend.planet.model;

import java.util.List;

import com.starwarsbackend.starwarsbackend.shared.model.PaginatedResponse;

public class PlanetPaginatedResponse extends PaginatedResponse<Planet> {
  public PlanetPaginatedResponse(List<Planet> results, int page, long total) {
    super(results, page, total);
  }
}
