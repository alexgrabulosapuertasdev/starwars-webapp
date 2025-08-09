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
    Comparator<Planet> comparator = CommonUtils.generateComparatorToSort(orderBy, orderDirection);

    List<Planet> filtered = planets.getResults().stream()
        .filter(planet -> planet.getName().toLowerCase().contains(search.toLowerCase()))
        .sorted(comparator)
        .toList();

    List<Planet> paginatedResults = filtered.stream()
        .skip((page - 1) * 15)
        .limit(15)
        .toList();

    return new PlanetPaginatedResponse(paginatedResults, page, filtered.size());
  }
}
