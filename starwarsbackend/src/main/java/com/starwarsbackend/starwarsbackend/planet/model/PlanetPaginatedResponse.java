package com.starwarsbackend.starwarsbackend.planet.model;

import java.util.List;

import com.starwarsbackend.starwarsbackend.shared.model.PaginatedResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Paginated response of planets")
public class PlanetPaginatedResponse extends PaginatedResponse<Planet> {
  public PlanetPaginatedResponse(List<Planet> results, int page, long total) {
    super(results, page, total);
  }
}
