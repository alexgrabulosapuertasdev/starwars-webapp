package com.starwarsbackend.starwarsbackend.people.model;

import com.starwarsbackend.starwarsbackend.shared.model.Sortable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "People model representing a Star Wars character")
@Data
public class People implements Sortable {
  private String name;
  private String height;
  private String mass;
  private String birthYear;
  private String gender;
  private String created;
}
