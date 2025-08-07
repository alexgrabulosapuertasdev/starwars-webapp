package com.starwarsbackend.starwarsbackend.people.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.starwarsbackend.starwarsbackend.people.model.People;
import com.starwarsbackend.starwarsbackend.people.model.PeoplePaginatedResponse;
import com.starwarsbackend.starwarsbackend.shared.model.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PeopleRepository {
  private final String API_URL_PEOPLE = Constants.API_URL + "people";

  private final RestTemplate restTemplate = new RestTemplate();

  PeoplePaginatedResponse peoplesData = new PeoplePaginatedResponse(new ArrayList<>(), 0, 0);

  @PostConstruct
  public void init() {
    fetchPeoples();
  }

  private void fetchPeoples() {
    List<People> peoples = new ArrayList<>();
    String baseUrl = API_URL_PEOPLE + "?page=";
    boolean hasMoreResults = true;

    try {
      for (int i = 1; hasMoreResults; i++) {
        Map<String, Object> response = restTemplate.getForObject(baseUrl + String.valueOf(i), Map.class);
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

        if (i == 1) {
          int total = (int) response.get("count");
          peoplesData.setTotal(total);
        }

        for (Map<String, Object> item : results) {
          People people = new People();

          people.setName((String) item.get("name"));
          people.setHeight((String) item.get("height"));
          people.setMass((String) item.get("mass"));
          people.setBirthYear((String) item.get("birth_year"));
          people.setGender((String) item.get("gender"));
          people.setCreated((String) item.get("created"));

          peoples.add(people);
        }

        hasMoreResults = response.get("next") != null;
      }

      this.peoplesData.setResults(peoples);
    } catch (RestClientException e) {
      log.error("Error fetching people from SWAPI: " + e.getMessage());
    }
  }

  public PeoplePaginatedResponse getAll() {
    return new PeoplePaginatedResponse(peoplesData.getResults(), peoplesData.getPage(), peoplesData.getTotal());
  }
}
