package com.starwarsbackend.starwarsbackend.planet.model;

import com.starwarsbackend.starwarsbackend.shared.model.Sortable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Planet model representing a Star Wars planet")
@Data
public class Planet implements Sortable {
  private String name;
  private String climate;
  private String gravity;
  private String terrain;
  private String population;
  private String created;
}
