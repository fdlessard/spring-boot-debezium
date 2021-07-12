package io.fdlessard.codebites.debezium.cdc.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.fdlessard.codebites.debezium.cdc.listener.model.Address;
import io.fdlessard.codebites.debezium.cdc.listener.model.CdcMessage;
import io.fdlessard.codebites.debezium.cdc.listener.model.Customer;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class PostgresCdcListenerApplication {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) {
    SpringApplication.run(PostgresCdcListenerApplication.class, args);
  }

  @Bean
  Consumer<String> customerChange() {

    return message -> {

      logger.info("customerChange() - Message: {}", message);

      try {
        CdcMessage<Customer> customerCdcMessage = deserializeCustomerCdcMessage(message);
        logger.info("customerChange() - customerCdcMessage: {}", customerCdcMessage);
        logger.info("customerChange() - Operation: {}",
            customerCdcMessage.getOp());
        logger.info("customerChange() - Customer(before): {}",
            customerCdcMessage.getBefore());
        logger.info("customerChange() - Customer(after): {}",
            customerCdcMessage.getAfter());

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
        CdcMessage<Address> addressCdcMessage = deserializeAddressCdcMessage(message);
        logger.info("addressChange() - addressCdcMessage: {}", addressCdcMessage);
        logger.info("addressChange() - Operation: {}",
            addressCdcMessage.getOp());
        logger.info("addressChange() - Address(before): {}",
            addressCdcMessage.getBefore());
        logger.info("addressChange() - Address(after): {}",
            addressCdcMessage.getAfter());

      } catch (JsonProcessingException e) {
        logger.error("addressChange()", e);
      }
    };
  }

  private CdcMessage<Customer> deserializeCustomerCdcMessage(String message)
      throws JsonProcessingException {

    return objectMapper.readValue(message, new TypeReference<>() {
    });

  }

  private CdcMessage<Address> deserializeAddressCdcMessage(String message)
      throws JsonProcessingException {

    return objectMapper.readValue(message, new TypeReference<>() {
    });

  }

}
