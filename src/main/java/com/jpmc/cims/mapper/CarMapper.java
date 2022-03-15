package com.jpmc.cims.mapper;

import com.jpmc.cims.dto.NewCarRequest;
import com.jpmc.cims.entity.Car;

public final class CarMapper {

  public static Car mapNewCarRequest(final NewCarRequest newCarRequest) {
    final Car car = new Car();
    car.setYear(newCarRequest.getYear());
    car.setMake(newCarRequest.getMake());
    car.setPrice(newCarRequest.getPrice());
    return car;
  }
}
