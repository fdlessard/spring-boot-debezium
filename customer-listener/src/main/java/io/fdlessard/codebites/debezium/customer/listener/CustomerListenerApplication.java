package io.fdlessard.codebites.debezium.customer.listener;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class CustomerListenerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerListenerApplication.class, args);
  }

  @Bean
  Consumer<String> receiveCustomer() {
    return message -> {
      logger.info("receiveCustomer() - Message: {}", message);
    };
  }

}
