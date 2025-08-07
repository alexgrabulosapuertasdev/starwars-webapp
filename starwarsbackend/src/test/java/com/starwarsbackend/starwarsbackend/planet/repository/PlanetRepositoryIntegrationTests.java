package com.starwarsbackend.starwarsbackend.planet.repository;

import com.starwarsbackend.starwarsbackend.planet.model.Planet;
import com.starwarsbackend.starwarsbackend.planet.model.PlanetPaginatedResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanetRepositoryIntegrationTests {

  @Autowired
  private PlanetRepository repository;

  @Test
  void testGetAllFetchesDataFromSwapi() {
    PlanetPaginatedResponse response = repository.getAll();

    assertNotNull(response);
    assertFalse(response.getResults().isEmpty(), "Expected non-empty results from SWAPI");
    assertTrue(response.getTotal() > 0, "Expected total count to be greater than zero");

    Planet first = response.getResults().get(0);
    assertNotNull(first.getName());
    assertNotNull(first.getClimate());
    assertNotNull(first.getGravity());
    assertNotNull(first.getTerrain());
    assertNotNull(first.getPopulation());
    assertNotNull(first.getCreated());
  }
}
