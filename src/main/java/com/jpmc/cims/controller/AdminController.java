package com.jpmc.cims.controller;

import com.jpmc.cims.dto.NewCarRequest;
import com.jpmc.cims.entity.Car;
import com.jpmc.cims.service.CarService;
import com.jpmc.cims.util.ApiConstants;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = ApiConstants.ADMIN_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

  @Autowired
  private CarService carService;

  @PostMapping("/add-car")
  public ResponseEntity<Car> addNewCar(@RequestBody @Valid final NewCarRequest newCarRequest) {
    log.debug("Admin request to add a new car");
    return new ResponseEntity<>(carService.addNew(newCarRequest), HttpStatus.CREATED);
  }

  @PostMapping("/remove")
  public ResponseEntity<Void> removeCars(@RequestBody final Set<UUID> ids) {
    log.debug("Admin request to remove cars with ids {}", ids);
    carService.remove(ids);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
