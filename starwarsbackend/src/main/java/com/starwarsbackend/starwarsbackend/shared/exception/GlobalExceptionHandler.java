package com.starwarsbackend.starwarsbackend.shared.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
  }

  @ExceptionHandler(RestClientException.class)
  public ResponseEntity<ErrorResponse> handleRestClientException(RestClientException e) {
    return buildResponse(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
  }

  private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
    ErrorResponse error = new ErrorResponse(
        status.value(),
        message,
        LocalDateTime.now());
    return new ResponseEntity<>(error, status);
  }
}
