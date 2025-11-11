package com.devtiro.tasks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.devtiro.tasks.domain.dto.ErrorResponse;

// This annotation handles the exetions across all our
// controllers in only one class
@ControllerAdvice
public class GlobalExceptionHandler {

  // ResponseEntity will give us a statuscode, body and a header. This is
  // used for the http request itself. Inside ErrorResponse we also have 
  // a statuscode for example, but the code will show up in the frontend. 
  // so two codes are needed.  
  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<ErrorResponse> handleExceptions(
    RuntimeException ex, WebRequest request
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.BAD_REQUEST.value(),
      ex.getMessage(),
      request.getDescription(false)
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
  
}
