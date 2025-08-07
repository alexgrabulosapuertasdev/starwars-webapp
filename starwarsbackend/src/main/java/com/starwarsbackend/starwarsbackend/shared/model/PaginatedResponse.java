package com.starwarsbackend.starwarsbackend.shared.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginatedResponse<T> {
  private List<T> results = new ArrayList<>();
  private int page;
  private long total;
}
