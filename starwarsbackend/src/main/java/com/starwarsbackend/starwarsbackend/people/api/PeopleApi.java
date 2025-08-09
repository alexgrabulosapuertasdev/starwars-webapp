package com.starwarsbackend.starwarsbackend.people.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starwarsbackend.starwarsbackend.people.model.PeoplePaginatedResponse;
import com.starwarsbackend.starwarsbackend.shared.exception.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/people")
public interface PeopleApi {
  @Operation(summary = "Get paginated list of people", description = "Search, sort and paginate Star Wars people")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(schema = @Schema(implementation = PeoplePaginatedResponse.class), mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
  })
  @GetMapping
  ResponseEntity<PeoplePaginatedResponse> findPeoples(
      @Parameter(description = "Partial name to search", example = "lu") @RequestParam(defaultValue = "") String search,
      @Parameter(description = "Page number (starts at 1)", example = "1") @RequestParam(defaultValue = "1") int page,
      @Parameter(description = "Order by field (name or created)", example = "name") @RequestParam(defaultValue = "name") String orderBy,
      @Parameter(description = "Order direction (asc or desc)", example = "asc") @RequestParam(defaultValue = "asc") String orderDirection);
}
