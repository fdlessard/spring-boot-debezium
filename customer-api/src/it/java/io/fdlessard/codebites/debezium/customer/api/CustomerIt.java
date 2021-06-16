package io.fdlessard.codebites.debezium.customer.api;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.fdlessard.codebites.debezium.customer.api.model.Customer;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Slf4j
@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
@ExtendWith( {SpringExtension.class})
public class CustomerIt extends BaseIt {

  @Autowired
  private OutputDestination outputDestination;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
        .build();
  }

  @Test
  void getCustomerById() throws Exception {

    mockMvc.perform(get("/customers/1"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  void createCustomer() throws Exception {

    mockMvc.perform(post("/asynchronous-customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(buildJsonStringCustomer()))
        .andExpect(status().is2xxSuccessful());

    Message<byte[]> outputMessage = outputDestination.receive(5000, "customers");
    String s = new String(outputMessage.getPayload(), StandardCharsets.UTF_8);
    assertNotNull(s);
    Customer customer = TestUtils.stringToPojo(s, Customer.class);
    assertCustomer(customer);
  }

  static void assertCustomer(Customer customer) {
    assertNotNull(customer);
    assertEquals("firstName", customer.getFirstName());
    assertEquals("lastName", customer.getLastName());
    assertEquals("company", customer.getCompany());
  }

  static String buildJsonStringCustomer() {
    return TestUtils.readFileIntoString("/Customer.json");
  }

}
