package com.jpmc.cims;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.cims.controller.CarController;
import com.jpmc.cims.dto.BuyCarRequest;
import com.jpmc.cims.dto.NewCarRequest;
import com.jpmc.cims.repository.CarRepository;
import com.jpmc.cims.util.ObjectBuilder;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CarControllerTests {

  @Autowired
  private CarController carController;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CarRepository carRepository;

  @Autowired
  private MockMvc mvc;

  @Test
  void contextLoads() {
    assertThat(carController).isNotNull();
  }

  @Test
  public void shouldReturnEmptyResponseIfNoCars() throws Exception {
    mvc.perform(get("/cims/car/list")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  public void shouldReturnCarsIfAvailable() throws Exception {
    createCar(ObjectBuilder.buildNewCarRequest());

    mvc.perform(get("/cims/car/list")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id", CoreMatchers.notNullValue()));
  }

  @Test
  public void shouldFailIfCarDoesNotExistWithId() throws Exception {
    final BuyCarRequest buyCarRequest = new BuyCarRequest();
    buyCarRequest.setId(UUID.randomUUID());

    mvc.perform(post("/cims/car/buy")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(buyCarRequest)))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldBuyCar() throws Exception {
    final NewCarRequest request = ObjectBuilder.buildNewCarRequest();
    request.setMake("Tesla");
    createCar(request);

    final UUID id = carRepository.list(false).get(0).getId();

    final BuyCarRequest buyCarRequest = new BuyCarRequest();
    buyCarRequest.setId(id);

    mvc.perform(post("/cims/car/buy")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(buyCarRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", CoreMatchers.equalTo(id.toString())));
  }

  @Test
  public void shouldFailIfCarAlreadyBought() throws Exception {
    final UUID id = carRepository.list(true).get(0).getId();

    final BuyCarRequest buyCarRequest = new BuyCarRequest();
    buyCarRequest.setId(id);

    mvc.perform(post("/cims/car/buy")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(buyCarRequest)))
        .andExpect(status().isBadRequest());
  }

  private void createCar(final NewCarRequest request) throws Exception {
    mvc.perform(post("/cims/admin/add-car").content(
            objectMapper.writeValueAsBytes(request)).contentType(
            MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  private void buyCar(final BuyCarRequest request) throws Exception {
    mvc.perform(post("/cims/car/buy").content(
            objectMapper.writeValueAsBytes(request)).contentType(
            MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}
