package io.fdlessard.codebites.debezium.customer.listener;

import static io.fdlessard.codebites.debezium.customer.listener.CustomerTestUtils.buildCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.fdlessard.codebites.debezium.customer.listener.model.Customer;
import io.fdlessard.codebites.debezium.customer.listener.repositories.CustomerRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Slf4j
@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
@ExtendWith( {SpringExtension.class})
public class CustomerIt extends BaseIt {

  @Autowired
  private InputDestination inputDestination;

  @Autowired
  private CustomerRepository customerRepository;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
        .build();
  }

  @Test
  void createCustomer() throws Exception {

    Message<Customer> inputMessage = MessageBuilder.withPayload(
        buildCustomer()
    ).build();

    inputDestination.send(inputMessage, "customers");
    Optional<Customer> customerOptional = customerRepository.findById(3l);
    assertTrue(customerOptional.isPresent());
    assertCustomer(customerOptional.get());
  }

  static void assertCustomer(Customer customer) {
    assertNotNull(customer);
    assertEquals("firstName", customer.getFirstName());
    assertEquals("lastName", customer.getLastName());
    assertEquals("company", customer.getCompany());
  }

}
