package com.starwarsbackend.starwarsbackend.people.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwarsbackend.starwarsbackend.people.model.People;
import com.starwarsbackend.starwarsbackend.people.model.PeoplePaginatedResponse;
import com.starwarsbackend.starwarsbackend.people.repository.PeopleRepository;
import com.starwarsbackend.starwarsbackend.shared.utils.CommonUtils;

@Service
public class PeopleService {
  @Autowired
  private PeopleRepository peopleRepository;

  PeopleService(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  public PeoplePaginatedResponse findPeoples(String search, int page, String orderBy, String orderDirection) {
    page = Math.max(page, 1);
    PeoplePaginatedResponse peoples = peopleRepository.getAll();
    Comparator<People> comparator = CommonUtils.generateComparatorToSort(orderBy, orderDirection);

    List<People> filtered = peoples.getResults().stream()
        .filter(people -> people.getName().toLowerCase().contains(search.toLowerCase()))
        .sorted(comparator)
        .skip((page - 1) * 15)
        .limit(15)
        .toList();

    return new PeoplePaginatedResponse(filtered, page, peoples.getTotal());
  }
}
