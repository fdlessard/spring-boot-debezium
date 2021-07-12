package io.fdlessard.codebites.debezium.cdc.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.fdlessard.codebites.debezium.cdc.listener.model.Address;
import io.fdlessard.codebites.debezium.cdc.listener.model.DebeziumCdcMessage;
import io.fdlessard.codebites.debezium.cdc.listener.model.Customer;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class PostgresDebeziumCdcListenerApplication {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) {
    SpringApplication.run(PostgresDebeziumCdcListenerApplication.class, args);
  }

  @Bean
  Consumer<String> customerChange() {

    return message -> {

      logger.info("customerChange() - Message: {}", message);

      try {
        DebeziumCdcMessage<Object, Customer> customerDebeziumCdcMessage = deserializeDebeziumCustomerCdcMessage(message);
        logger.info("customerChange() - customerDebeziumCdcMessage: {}", customerDebeziumCdcMessage);
        logger.info("customerChange() - Payload: {}", customerDebeziumCdcMessage.getPayload());
        logger.info("customerChange() - Payload Operation: {}",
            customerDebeziumCdcMessage.getPayload().getOp());
        logger.info("customerChange() - Payload Customer(before): {}",
            customerDebeziumCdcMessage.getPayload().getBefore());
        logger.info("customerChange() - Payload Customer(after): {}",
            customerDebeziumCdcMessage.getPayload().getAfter());

      } catch (JsonProcessingException e) {
        logger.error("customerChange()", e);
      }
    };
  }

  @Bean
  Consumer<String> addressChange() {

    return message -> {

      logger.info("addressChange() - Message: {}", message);

      try {
        DebeziumCdcMessage<Object, Address> addressDebeziumCdcMessage = deserializeAddressDebeziumCdcMessage(message);
        logger.info("addressChange() - addressDebeziumCdcMessage: {}", addressDebeziumCdcMessage);
        logger.info("addressChange() - Payload: {}", addressDebeziumCdcMessage.getPayload());
        logger.info("addressChange() - Payload Operation: {}",
            addressDebeziumCdcMessage.getPayload().getOp());
        logger.info("addressChange() - Payload Address(before): {}",
            addressDebeziumCdcMessage.getPayload().getBefore());
        logger.info("addressChange() - Payload Address(after): {}",
            addressDebeziumCdcMessage.getPayload().getAfter());

      } catch (JsonProcessingException e) {
        logger.error("addressChange()", e);
      }
    };
  }

  private DebeziumCdcMessage<Object, Customer> deserializeDebeziumCustomerCdcMessage(String message)
      throws JsonProcessingException {

    return objectMapper.readValue(message, new TypeReference<>() {
    });

  }

  private DebeziumCdcMessage<Object, Address> deserializeAddressDebeziumCdcMessage(String message)
      throws JsonProcessingException {

    return objectMapper.readValue(message, new TypeReference<>() {
    });

  }

}
