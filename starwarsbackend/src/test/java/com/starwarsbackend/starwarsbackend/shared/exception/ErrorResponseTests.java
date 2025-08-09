package com.starwarsbackend.starwarsbackend.shared.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

  @Test
  void testConstructorAndGetters() {
    int status = HttpStatus.NOT_FOUND.value();
    String message = "Not Found";
    LocalDateTime timestamp = LocalDateTime.now();

    ErrorResponse errorResponse = new ErrorResponse(status, message, timestamp);

    assertEquals(status, errorResponse.getStatus());
    assertEquals(message, errorResponse.getMessage());
    assertEquals(timestamp, errorResponse.getTimestamp());
  }

  @Test
  void testSetters() {
    ErrorResponse errorResponse = new ErrorResponse(0, null, null);

    int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    String message = "Internal Server Error";
    LocalDateTime timestamp = LocalDateTime.now();

    errorResponse.setStatus(status);
    errorResponse.setMessage(message);
    errorResponse.setTimestamp(timestamp);

    assertEquals(status, errorResponse.getStatus());
    assertEquals(message, errorResponse.getMessage());
    assertEquals(timestamp, errorResponse.getTimestamp());
  }
}
