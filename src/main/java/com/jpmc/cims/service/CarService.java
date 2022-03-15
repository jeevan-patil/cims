package com.jpmc.cims.service;

import com.jpmc.cims.dto.BuyCarRequest;
import com.jpmc.cims.dto.NewCarRequest;
import com.jpmc.cims.entity.Car;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CarService {

  /**
   * This method adds a new car in the inventory.
   *
   * @param newCarRequest New car request object.
   * @return {@code Car} New car response.
   */
  Car addNew(NewCarRequest newCarRequest);

  /**
   * This method lists all the cars.
   *
   * @return {@code List<Car>} The cars list.
   * @param sold
   */
  List<Car> list(Boolean sold);

  /**
   * This method processes the buying of cars.
   *
   * @param buyCarRequest Buy car requests
   */
  Car buy(final BuyCarRequest buyCarRequest);

  /**
   * A method to remove the cars from inventory.
   *
   * @param ids Car ids.
   */
  void remove(final Set<UUID> ids);
}
