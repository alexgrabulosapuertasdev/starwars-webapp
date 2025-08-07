package com.starwarsbackend.starwarsbackend.people.model;

import lombok.Data;

@Data
public class People {
  private String name;
  private String height;
  private String mass;
  private String birthYear;
  private String gender;
  private String created;
}
