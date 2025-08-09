package com.starwarsbackend.starwarsbackend.people;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PeopleIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetPeopleDefault() throws Exception {
    mockMvc.perform(get("/people"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", hasSize(lessThanOrEqualTo(15))))
        .andExpect(jsonPath("$.page", is(1)))
        .andExpect(jsonPath("$.total", greaterThan(0)))
        .andExpect(jsonPath("$.results[0].name", not(emptyString())));
  }

  @Test
  public void testSearchPeople() throws Exception {
    mockMvc.perform(get("/people")
        .param("search", "lu"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", not(empty())))
        .andExpect(jsonPath("$.results[0].name", containsStringIgnoringCase("lu")));
  }

  @Test
  public void testPagination() throws Exception {
    mockMvc.perform(get("/people")
        .param("page", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.page", is(2)))
        .andExpect(jsonPath("$.results", hasSize(lessThanOrEqualTo(15))));
  }

  @Test
  public void testSortingDescByCreated() throws Exception {
    mockMvc.perform(get("/people")
        .param("orderBy", "created")
        .param("orderDirection", "desc"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", hasSize(lessThanOrEqualTo(15))));
  }
}
