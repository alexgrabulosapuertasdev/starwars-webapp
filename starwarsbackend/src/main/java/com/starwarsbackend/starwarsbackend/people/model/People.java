package com.starwarsbackend.starwarsbackend.people.model;

import com.starwarsbackend.starwarsbackend.shared.model.Sortable;

import lombok.Data;

@Data
public class People implements Sortable {
  private String name;
  private String height;
  private String mass;
  private String birthYear;
  private String gender;
  private String created;
}
