package com.starwarsbackend.starwarsbackend.planet.model;

import lombok.Data;

@Data
public class Planet {
  private String name;
  private String climate;
  private String gravity;
  private String terrain;
  private String population;
  private String created;
}
