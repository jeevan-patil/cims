package com.jpmc.cims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home() {
    return "redirect:/cims";
  }

  @RequestMapping(value = "/cims", method = RequestMethod.GET)
  public String app() {
    return "Car Inventory Management and Sales";
  }

}
