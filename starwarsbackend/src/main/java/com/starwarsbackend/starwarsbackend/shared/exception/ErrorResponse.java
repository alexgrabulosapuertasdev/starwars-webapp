package com.starwarsbackend.starwarsbackend.shared.exception;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Schema(description = "Error response model")
public class ErrorResponse {
  @Schema(description = "HTTP status code", example = "500")
  private int status;
  @Schema(description = "Error message", example = "Internal server error")
  private String message;
  @Schema(description = "Timestamp of the error", example = "2025-08-09T14:33:45.123")
  private LocalDateTime timestamp;
}
