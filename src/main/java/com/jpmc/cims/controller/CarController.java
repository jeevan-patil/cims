package com.jpmc.cims.controller;

import com.jpmc.cims.dto.BuyCarRequest;
import com.jpmc.cims.entity.Car;
import com.jpmc.cims.service.CarService;
import com.jpmc.cims.util.ApiConstants;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = ApiConstants.CAR_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

  @Autowired
  private CarService carService;

  @GetMapping("/list")
  public ResponseEntity<List<Car>> list(
      @RequestParam(required = false, name = "sold") final Boolean sold) {
    log.debug("Incoming request to list the available cars");
    return new ResponseEntity<>(carService.list(sold), HttpStatus.OK);
  }

  @PostMapping("/buy")
  public ResponseEntity<Car> buy(@RequestBody final BuyCarRequest buyCarRequest) {
    log.debug("Incoming request to buy the cars");
    return new ResponseEntity<>(carService.buy(buyCarRequest), HttpStatus.OK);
  }

}
