package com.jpmc.cims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CarAlreadySoldException extends RuntimeException {

  public CarAlreadySoldException(final String errorMessage) {
    super(errorMessage);
  }

}
