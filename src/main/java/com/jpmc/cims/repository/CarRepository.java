package com.jpmc.cims.repository;

import com.jpmc.cims.dto.NewCarRequest;
import com.jpmc.cims.entity.Car;
import com.jpmc.cims.mapper.CarMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CarRepository {

  private final Map<UUID, Car> CARS = new HashMap<>();

  public Car save(final Car car) {
    return car;
  }

  public Car addNew(final NewCarRequest newCarRequest) {
    final Car car = CarMapper.mapNewCarRequest(newCarRequest);
    CARS.put(car.getId(), car);
    return car;
  }

  public List<Car> list(final Boolean sold) {
    final List<Car> cars = new ArrayList<>();

    for (Map.Entry<UUID, Car> book : CARS.entrySet()) {
      final Car car = book.getValue();

      if (Objects.isNull(sold)) {
        cars.add(car);
        continue;
      }

      if (sold.equals(car.isSold())) {
        cars.add(car);
      }
    }

    return cars;
  }

  public Car findById(final UUID id) {
    return CARS.get(id);
  }

  public void removeById(final UUID id) {
    CARS.remove(id);
  }

  public void removeAllById(final Set<UUID> ids) {
    for (final UUID id : ids) {
      CARS.remove(id);
    }
  }

  public void update(final UUID id, final Car car) {
    CARS.put(id, car);
  }
}
