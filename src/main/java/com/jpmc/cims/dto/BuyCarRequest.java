package com.jpmc.cims.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuyCarRequest {

  @NotNull(message = "ID of the car is mandatory")
  private UUID id;

  private Double buyingPrice;
}
