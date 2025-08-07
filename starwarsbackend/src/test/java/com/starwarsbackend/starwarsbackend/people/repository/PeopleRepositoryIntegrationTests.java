package com.starwarsbackend.starwarsbackend.people.repository;

import com.starwarsbackend.starwarsbackend.people.model.People;
import com.starwarsbackend.starwarsbackend.people.model.PeoplePaginatedResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeopleRepositoryIntegrationTests {

  @Autowired
  private PeopleRepository repository;

  @Test
  void testGetAllFetchesDataFromSwapi() {
    PeoplePaginatedResponse response = repository.getAll();

    assertNotNull(response);
    assertFalse(response.getResults().isEmpty(), "Expected non-empty results from SWAPI");
    assertTrue(response.getTotal() > 0, "Expected total count to be greater than zero");

    People first = response.getResults().get(0);
    assertNotNull(first.getName());
    assertNotNull(first.getHeight());
    assertNotNull(first.getMass());
  }
}
