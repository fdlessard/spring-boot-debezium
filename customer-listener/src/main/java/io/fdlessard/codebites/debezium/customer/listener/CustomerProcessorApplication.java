package io.fdlessard.codebites.debezium.customer.listener;

import io.fdlessard.codebites.debezium.customer.listener.services.CustomerService;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@Slf4j
@SpringBootApplication
public class CustomerProcessorApplication {

  @Autowired
  private CustomerService customerService;

  public static void main(String[] args) {
    SpringApplication.run(CustomerProcessorApplication.class, args);
  }

  @Bean
  Consumer<Message<Object>> receiveCustomer() {
    return message -> {
      logger.info("receiveCustomer() - Message: {}", message);
      MessageHeaders messageHeaders = message.getHeaders();
      logger.info("receiveCustomer() - MessageHeaders: {}", messageHeaders);
      Object receivedCustomer = message.getPayload();
      logger.info("receiveCustomer() - Payload: {}", receivedCustomer);

      //customerService.createCustomer(receivedCustomer);
    };
  }

}
