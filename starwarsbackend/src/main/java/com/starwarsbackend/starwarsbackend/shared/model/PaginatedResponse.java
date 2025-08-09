package com.starwarsbackend.starwarsbackend.shared.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Generic paginated response")
public class PaginatedResponse<T> {
  @Schema(description = "List of results")
  private List<T> results = new ArrayList<>();
  @Schema(description = "Current page number")
  private int page;
  @Schema(description = "Total number of items")
  private long total;
}
