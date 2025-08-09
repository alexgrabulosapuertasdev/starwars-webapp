package com.starwarsbackend.starwarsbackend.planet;

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
public class PlanetIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetPlanetsDefault() throws Exception {
    mockMvc.perform(get("/planet"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", hasSize(lessThanOrEqualTo(15))))
        .andExpect(jsonPath("$.page", is(1)))
        .andExpect(jsonPath("$.total", greaterThan(0)))
        .andExpect(jsonPath("$.results[0].name", not(emptyString())));
  }

  @Test
  public void testSearchPlanets() throws Exception {
    mockMvc.perform(get("/planet")
        .param("search", "Alderaan"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", not(empty())))
        .andExpect(jsonPath("$.results[0].name", containsStringIgnoringCase("Alderaan")));
  }

  @Test
  public void testPagination() throws Exception {
    mockMvc.perform(get("/planet")
        .param("page", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.page", is(2)))
        .andExpect(jsonPath("$.results", hasSize(lessThanOrEqualTo(15))));
  }

  @Test
  public void testSortingDescByCreated() throws Exception {
    mockMvc.perform(get("/planet")
        .param("orderBy", "created")
        .param("orderDirection", "desc"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", hasSize(lessThanOrEqualTo(15))));
  }
}
