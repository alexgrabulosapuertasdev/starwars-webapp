package com.starwarsbackend.starwarsbackend.people.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starwarsbackend.starwarsbackend.people.model.PeoplePaginatedResponse;

@RequestMapping("/people")
public interface PeopleApi {
  @GetMapping
  ResponseEntity<PeoplePaginatedResponse> findPeoples(
      @RequestParam(defaultValue = "") String search,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "name") String orderBy,
      @RequestParam(defaultValue = "asc") String orderDirection);
}
