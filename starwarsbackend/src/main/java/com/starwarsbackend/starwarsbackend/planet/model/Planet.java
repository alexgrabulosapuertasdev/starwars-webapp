package com.starwarsbackend.starwarsbackend.planet.model;

import com.starwarsbackend.starwarsbackend.shared.model.Sortable;

import lombok.Data;

@Data
public class Planet implements Sortable {
  private String name;
  private String climate;
  private String gravity;
  private String terrain;
  private String population;
  private String created;
}
