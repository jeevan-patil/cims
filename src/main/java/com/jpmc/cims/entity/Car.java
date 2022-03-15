package com.jpmc.cims.entity;

import java.time.Year;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Car {

  @Setter(AccessLevel.NONE)
  private UUID id;
  private String make;
  private Year year;
  private Double price;
  private boolean sold;

  public Car() {
    this.id = UUID.randomUUID();
    this.sold = false;
  }
}
