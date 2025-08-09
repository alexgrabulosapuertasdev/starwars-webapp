package com.starwarsbackend.starwarsbackend.people.service;

import com.starwarsbackend.starwarsbackend.people.model.People;
import com.starwarsbackend.starwarsbackend.people.model.PeoplePaginatedResponse;
import com.starwarsbackend.starwarsbackend.people.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PeopleServiceTests {

  private PeopleRepository repository;
  private PeopleService service;

  @BeforeEach
  void setUp() {
    repository = mock(PeopleRepository.class);
    service = new PeopleService(repository);
  }

  @Test
  void findPeoples_filtersOrdersAndPaginatesCorrectly() {
    People luke = new People();
    luke.setName("Luke Skywalker");
    luke.setCreated("2020-01-01");
    People leia = new People();
    leia.setName("Leia Organa");
    leia.setCreated("2020-01-02");
    People han = new People();
    han.setName("Han Solo");
    han.setCreated("2020-01-03");

    List<People> allPeople = List.of(luke, leia, han);
    when(repository.getAll()).thenReturn(new PeoplePaginatedResponse(allPeople, 1, 3));

    PeoplePaginatedResponse result = service.findPeoples("lu", 1, "name", "desc");

    List<People> filtered = result.getResults();
    assertEquals(1, filtered.size());
    assertEquals("Luke Skywalker", filtered.get(0).getName());
  }

  @Test
  void findPeoples_ordersByCreatedAsc() {
    People p1 = new People();
    p1.setName("A");
    p1.setCreated("2020-01-02");
    People p2 = new People();
    p2.setName("B");
    p2.setCreated("2020-01-01");

    when(repository.getAll()).thenReturn(new PeoplePaginatedResponse(List.of(p1, p2), 1, 2));

    PeoplePaginatedResponse result = service.findPeoples("", 1, "created", "asc");

    assertEquals("B", result.getResults().get(0).getName());
    assertEquals("A", result.getResults().get(1).getName());
  }

  @Test
  void findPeoples_ordersByCreatedDesc() {
    People p1 = new People();
    p1.setName("A");
    p1.setCreated("2020-01-02");
    People p2 = new People();
    p2.setName("B");
    p2.setCreated("2020-01-01");

    when(repository.getAll()).thenReturn(new PeoplePaginatedResponse(List.of(p1, p2), 1, 2));

    PeoplePaginatedResponse result = service.findPeoples("", 1, "created", "desc");

    assertEquals("A", result.getResults().get(0).getName());
    assertEquals("B", result.getResults().get(1).getName());
  }

  @Test
  void findPeoples_returnsEmptyWhenNoMatch() {
    People p1 = new People();
    p1.setName("Luke Skywalker");
    when(repository.getAll()).thenReturn(new PeoplePaginatedResponse(List.of(p1), 1, 1));

    PeoplePaginatedResponse result = service.findPeoples("zzz", 1, "name", "asc");

    assertTrue(result.getResults().isEmpty());
  }

  @Test
  void findPeoples_returnsAllWhenSearchIsEmpty() {
    People p1 = new People();
    p1.setName("A");
    People p2 = new People();
    p2.setName("B");
    when(repository.getAll()).thenReturn(new PeoplePaginatedResponse(List.of(p1, p2), 1, 2));

    PeoplePaginatedResponse result = service.findPeoples("", 1, "name", "asc");

    assertEquals(2, result.getResults().size());
  }

  @Test
  void findPeoples_pageLessThanOneDefaultsToOne() {
    People p1 = new People();
    p1.setName("Test");
    when(repository.getAll()).thenReturn(new PeoplePaginatedResponse(List.of(p1), 1, 1));

    PeoplePaginatedResponse result = service.findPeoples("", 0, "name", "asc");

    assertEquals(1, result.getResults().size());
  }

  @Test
  void findPeoples_defaultOrderByNameWhenEmpty() {
    People a = new People();
    a.setName("A");
    People b = new People();
    b.setName("B");

    when(repository.getAll()).thenReturn(new PeoplePaginatedResponse(List.of(b, a), 1, 2));

    PeoplePaginatedResponse result = service.findPeoples("", 1, "", "asc");

    assertEquals("A", result.getResults().get(0).getName());
  }

  @Test
  void findPeoples_limitsTo15ResultsPerPage() {
    List<People> peopleList = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
      People p = new People();
      p.setName("Person " + i);
      p.setCreated("2020-01-01");
      peopleList.add(p);
    }

    when(repository.getAll()).thenReturn(new PeoplePaginatedResponse(peopleList, 1, 30));

    PeoplePaginatedResponse resultPage1 = service.findPeoples("Person", 1, "name", "asc");
    PeoplePaginatedResponse resultPage2 = service.findPeoples("Person", 2, "name", "asc");

    assertEquals(30, resultPage1.getTotal());
    assertEquals(15, resultPage1.getResults().size());
    assertEquals(15, resultPage2.getResults().size());
    assertEquals("Person 0", resultPage1.getResults().get(0).getName());
    assertEquals("Person 22", resultPage2.getResults().get(0).getName());
  }
}
