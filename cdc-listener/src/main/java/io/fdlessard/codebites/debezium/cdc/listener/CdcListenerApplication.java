package io.fdlessard.codebites.debezium.cdc.listener;

import io.debezium.serde.DebeziumSerdes;
import io.fdlessard.codebites.debezium.cdc.listener.model.Customer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class CdcListenerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CdcListenerApplication.class, args);
  }

  @Bean
  Consumer<String> customerChange() {
    return message -> {
      logger.info("customerChange() - Message: {}", message);
      Customer customer = deserializeCustomer(message);
    };
  }

  @Bean
  Consumer<String> addressChange() {
    return message -> {
      logger.info("addressChange() - Message: {}", message);
    };
  }

  private Customer deserializeCustomer(String message) {

    Serde<Customer> serde = DebeziumSerdes.payloadJson(Customer.class);
    serde.configure(Collections.singletonMap("from.field", "after"), false);
    Customer customer = serde.deserializer().deserialize("dbserver1.public.customer", message.getBytes(StandardCharsets.UTF_8));
    logger.info("deserializeCustomer() - Customer: {}", customer);

    return customer;

  }

}
