package com.starwarsbackend.starwarsbackend.people.model;

import java.util.List;

import com.starwarsbackend.starwarsbackend.shared.model.PaginatedResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Paginated response of People")
public class PeoplePaginatedResponse extends PaginatedResponse<People> {
  public PeoplePaginatedResponse(List<People> results, int page, long total) {
    super(results, page, total);
  }
}
