package com.jpmc.cims.util;

import com.jpmc.cims.dto.NewCarRequest;
import java.time.Year;

public final class ObjectBuilder {

  public static NewCarRequest buildNewCarRequest() {
    final NewCarRequest request = new NewCarRequest();
    request.setMake("Ford");
    request.setYear(Year.of(2009));
    request.setPrice(3456.34);
    return request;
  }
}
