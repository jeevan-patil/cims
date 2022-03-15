package com.jpmc.cims.service;

import com.jpmc.cims.dto.BuyCarRequest;
import com.jpmc.cims.dto.NewCarRequest;
import com.jpmc.cims.entity.Car;
import com.jpmc.cims.exception.CarAlreadySoldException;
import com.jpmc.cims.exception.CarNotFoundException;
import com.jpmc.cims.repository.CarRepository;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CarServiceImpl implements CarService {

  @Autowired
  private CarRepository carRepository;

  @Override
  public Car addNew(final NewCarRequest newCarRequest) {
    log.debug("Saving a new car with make {}", newCarRequest.getMake());
    return carRepository.addNew(newCarRequest);
  }

  @Override
  public List<Car> list(final Boolean sold) {
    return carRepository.list(sold);
  }

  @Override
  public Car buy(final BuyCarRequest buyCarRequest) {
    log.debug("Processing the car purchase {}", buyCarRequest);
    final UUID id = buyCarRequest.getId();
    final Car car = carRepository.findById(id);

    if (Objects.isNull(car)) {
      throw new CarNotFoundException("Car with specified id not found");
    }

    if (car.isSold()) {
      throw new CarAlreadySoldException("Car has been sold already");
    }

    final Double price = buyCarRequest.getBuyingPrice();
    if (Objects.nonNull(price)) {
      car.setPrice(price);
    }

    car.setSold(true);
    carRepository.update(id, car);
    return car;
  }

  @Override
  public void remove(final Set<UUID> ids) {
    log.debug("Remove cars from the inventory {}", ids);
    carRepository.removeAllById(ids);
  }

}
