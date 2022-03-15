package com.jpmc.cims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException {

  public CarNotFoundException(final String errorMessage) {
    super(errorMessage);
  }

}
