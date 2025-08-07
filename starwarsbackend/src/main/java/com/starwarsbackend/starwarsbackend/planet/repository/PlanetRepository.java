package com.starwarsbackend.starwarsbackend.planet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.starwarsbackend.starwarsbackend.planet.model.Planet;
import com.starwarsbackend.starwarsbackend.planet.model.PlanetPaginatedResponse;
import com.starwarsbackend.starwarsbackend.shared.model.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PlanetRepository {
  private final String API_URL_PLANET = Constants.API_URL + "planets";

  private final RestTemplate restTemplate = new RestTemplate();

  PlanetPaginatedResponse planetsData = new PlanetPaginatedResponse(new ArrayList<>(), 0, 0);

  @PostConstruct
  public void init() {
    fetchPlanets();
  }

  private void fetchPlanets() {
    List<Planet> planets = new ArrayList<>();
    String baseUrl = API_URL_PLANET + "?page=";
    boolean hasMoreResults = true;

    try {
      for (int i = 1; hasMoreResults; i++) {
        Map<String, Object> response = restTemplate.getForObject(baseUrl + String.valueOf(i), Map.class);
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

        if (i == 1) {
          int total = (int) response.get("count");
          planetsData.setTotal(total);
        }

        for (Map<String, Object> item : results) {
          Planet planet = new Planet();

          planet.setName((String) item.get("name"));
          planet.setClimate((String) item.get("climate"));
          planet.setGravity((String) item.get("gravity"));
          planet.setTerrain((String) item.get("terrain"));
          planet.setPopulation((String) item.get("population"));
          planet.setCreated((String) item.get("created"));

          planets.add(planet);
        }

        hasMoreResults = response.get("next") != null;
      }

      this.planetsData.setResults(planets);
    } catch (RestClientException e) {
      log.error("Error fetching planet from SWAPI: " + e.getMessage());
    }
  }

  public PlanetPaginatedResponse getAll() {
    return new PlanetPaginatedResponse(planetsData.getResults(), planetsData.getPage(), planetsData.getTotal());
  }
}
