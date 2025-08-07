package com.starwarsbackend.starwarsbackend.people.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.starwarsbackend.starwarsbackend.people.api.PeopleApi;
import com.starwarsbackend.starwarsbackend.people.model.PeoplePaginatedResponse;
import com.starwarsbackend.starwarsbackend.people.service.PeopleService;

@RestController
public class PeopleController implements PeopleApi {
  @Autowired
  private PeopleService peopleService;

  @Override
  public ResponseEntity<PeoplePaginatedResponse> findPeoples(String search, int page, String orderBy,
      String orderDirection) {
    return ResponseEntity.ok(peopleService.findPeoples(search, page, orderBy, orderDirection));
  }
}
