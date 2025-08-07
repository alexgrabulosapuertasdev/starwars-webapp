package com.starwarsbackend.starwarsbackend.planet.service;

import com.starwarsbackend.starwarsbackend.planet.model.Planet;
import com.starwarsbackend.starwarsbackend.planet.model.PlanetPaginatedResponse;
import com.starwarsbackend.starwarsbackend.planet.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlanetServiceTests {

  private PlanetRepository repository;
  private PlanetService service;

  @BeforeEach
  void setUp() {
    repository = mock(PlanetRepository.class);
    service = new PlanetService(repository);
  }

  @Test
  void findPlanets_filtersOrdersAndPaginatesCorrectly() {
    Planet luke = new Planet();
    luke.setName("Tatooine");
    luke.setCreated("2020-01-01");
    Planet leia = new Planet();
    leia.setName("Alderaan");
    leia.setCreated("2020-01-02");
    Planet han = new Planet();
    han.setName("Yavin IV");
    han.setCreated("2020-01-03");

    List<Planet> allPlanet = List.of(luke, leia, han);
    when(repository.getAll()).thenReturn(new PlanetPaginatedResponse(allPlanet, 1, 3));

    PlanetPaginatedResponse result = service.findPlanets("ta", 1, "name", "desc");

    List<Planet> filtered = result.getResults();
    assertEquals(1, filtered.size());
    assertEquals("Tatooine", filtered.get(0).getName());
  }

  @Test
  void findPlanets_ordersByCreatedAsc() {
    Planet p1 = new Planet();
    p1.setName("A");
    p1.setCreated("2020-01-02");
    Planet p2 = new Planet();
    p2.setName("B");
    p2.setCreated("2020-01-01");

    when(repository.getAll()).thenReturn(new PlanetPaginatedResponse(List.of(p1, p2), 1, 2));

    PlanetPaginatedResponse result = service.findPlanets("", 1, "created", "asc");

    assertEquals("B", result.getResults().get(0).getName());
    assertEquals("A", result.getResults().get(1).getName());
  }

  @Test
  void findPlanets_ordersByCreatedDesc() {
    Planet p1 = new Planet();
    p1.setName("A");
    p1.setCreated("2020-01-02");
    Planet p2 = new Planet();
    p2.setName("B");
    p2.setCreated("2020-01-01");

    when(repository.getAll()).thenReturn(new PlanetPaginatedResponse(List.of(p1, p2), 1, 2));

    PlanetPaginatedResponse result = service.findPlanets("", 1, "created", "desc");

    assertEquals("A", result.getResults().get(0).getName());
    assertEquals("B", result.getResults().get(1).getName());
  }

  @Test
  void findPlanets_returnsEmptyWhenNoMatch() {
    Planet p1 = new Planet();
    p1.setName("Tatooine");
    when(repository.getAll()).thenReturn(new PlanetPaginatedResponse(List.of(p1), 1, 1));

    PlanetPaginatedResponse result = service.findPlanets("zzz", 1, "name", "asc");

    assertTrue(result.getResults().isEmpty());
  }

  @Test
  void findPlanets_returnsAllWhenSearchIsEmpty() {
    Planet p1 = new Planet();
    p1.setName("A");
    Planet p2 = new Planet();
    p2.setName("B");
    when(repository.getAll()).thenReturn(new PlanetPaginatedResponse(List.of(p1, p2), 1, 2));

    PlanetPaginatedResponse result = service.findPlanets("", 1, "name", "asc");

    assertEquals(2, result.getResults().size());
  }

  @Test
  void findPlanets_pageLessThanOneDefaultsToOne() {
    Planet p1 = new Planet();
    p1.setName("Test");
    when(repository.getAll()).thenReturn(new PlanetPaginatedResponse(List.of(p1), 1, 1));

    PlanetPaginatedResponse result = service.findPlanets("", 0, "name", "asc");

    assertEquals(1, result.getResults().size());
  }

  @Test
  void findPlanets_defaultOrderByNameWhenEmpty() {
    Planet a = new Planet();
    a.setName("A");
    Planet b = new Planet();
    b.setName("B");

    when(repository.getAll()).thenReturn(new PlanetPaginatedResponse(List.of(b, a), 1, 2));

    PlanetPaginatedResponse result = service.findPlanets("", 1, "", "asc");

    assertEquals("A", result.getResults().get(0).getName());
  }

  @Test
  void findPlanets_limitsTo15ResultsPerPage() {
    List<Planet> planetList = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
      Planet p = new Planet();
      p.setName("Planet " + i);
      p.setCreated("2020-01-01");
      planetList.add(p);
    }

    when(repository.getAll()).thenReturn(new PlanetPaginatedResponse(planetList, 1, 30));

    PlanetPaginatedResponse resultPage1 = service.findPlanets("Planet", 1, "name", "asc");
    PlanetPaginatedResponse resultPage2 = service.findPlanets("Planet", 2, "name", "asc");

    assertEquals(30, resultPage1.getTotal());
    assertEquals(15, resultPage1.getResults().size());
    assertEquals(15, resultPage2.getResults().size());
    assertEquals("Planet 0", resultPage1.getResults().get(0).getName());
    assertEquals("Planet 22", resultPage2.getResults().get(0).getName());
  }
}
