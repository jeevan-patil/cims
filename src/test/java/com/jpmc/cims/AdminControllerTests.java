package com.jpmc.cims;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.cims.controller.AdminController;
import com.jpmc.cims.dto.NewCarRequest;
import com.jpmc.cims.repository.CarRepository;
import com.jpmc.cims.util.ObjectBuilder;
import java.util.HashSet;
import java.util.Set;
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
public class AdminControllerTests {

  @Autowired
  private AdminController adminController;

  @Autowired
  private CarRepository carRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mvc;

  @Test
  void contextLoads() {
    assertThat(adminController).isNotNull();
  }

  @Test
  public void shouldCreateCar() throws Exception {
    mvc.perform(post("/cims/admin/add-car").content(
                objectMapper.writeValueAsBytes(ObjectBuilder.buildNewCarRequest()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", CoreMatchers.notNullValue()));
  }

  @Test
  public void shouldFailIfInsufficientDataProvided() throws Exception {
    final NewCarRequest request = ObjectBuilder.buildNewCarRequest();
    request.setMake(null);

    mvc.perform(post("/cims/admin/add-car").content(
                objectMapper.writeValueAsBytes(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldRemoveCar() throws Exception {
    final UUID id = carRepository.list(null).get(0).getId();
    final Set<String> carIds = new HashSet<>();
    carIds.add(id.toString());

    mvc.perform(post("/cims/admin/remove").content(
                objectMapper.writeValueAsBytes(carIds))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}
