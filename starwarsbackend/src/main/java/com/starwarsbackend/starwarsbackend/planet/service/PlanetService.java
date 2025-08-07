package com.starwarsbackend.starwarsbackend.planet.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwarsbackend.starwarsbackend.planet.model.Planet;
import com.starwarsbackend.starwarsbackend.planet.model.PlanetPaginatedResponse;
import com.starwarsbackend.starwarsbackend.planet.repository.PlanetRepository;
import com.starwarsbackend.starwarsbackend.shared.utils.CommonUtils;

@Service
public class PlanetService {
  @Autowired
  private PlanetRepository planetRepository;

  PlanetService(PlanetRepository planetRepository) {
    this.planetRepository = planetRepository;
  }

  public PlanetPaginatedResponse findPlanets(String search, int page, String orderBy, String orderDirection) {
    page = Math.max(page, 1);
    PlanetPaginatedResponse planets = planetRepository.getAll();
    Comparator<Planet> comparator = generateComparatorToSortPlanet(orderBy, orderDirection);

    List<Planet> filtered = planets.getResults().stream()
        .filter(planet -> planet.getName().toLowerCase().contains(search.toLowerCase()))
        .sorted(comparator)
        .skip((page - 1) * 15)
        .limit(15)
        .toList();

    return new PlanetPaginatedResponse(filtered, page, planets.getTotal());
  }

  private Comparator<Planet> generateComparatorToSortPlanet(String orderBy, String orderDirection) {
    if (CommonUtils.isEmpty(orderBy)) {
      orderBy = "name";
    }

    Comparator<Planet> comparator = switch (orderBy.toLowerCase()) {
      case "name" -> Comparator.comparing(Planet::getName, String.CASE_INSENSITIVE_ORDER);
      case "created" -> Comparator.comparing(Planet::getCreated);
      default -> Comparator.comparing(Planet::getName, String.CASE_INSENSITIVE_ORDER);
    };

    boolean isReversed = "desc".equalsIgnoreCase(orderDirection);

    if (isReversed) {
      comparator = comparator.reversed();
    }

    return comparator;
  }
}
