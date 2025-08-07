package com.starwarsbackend.starwarsbackend.people.model;

import java.util.List;

import com.starwarsbackend.starwarsbackend.shared.model.PaginatedResponse;

public class PeoplePaginatedResponse extends PaginatedResponse<People> {
  public PeoplePaginatedResponse(List<People> results, int page, long total) {
    super(results, page, total);
  }
}
