package com.jpmc.cims.dto;

import java.time.Year;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewCarRequest {

  @NotEmpty(message = "Car make cannot be empty")
  private String make;

  @NotNull(message = "Car year cannot be null")
  private Year year;

  @NotNull(message = "Car listing price is mandatory")
  private Double price;
}
