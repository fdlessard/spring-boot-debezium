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
public class CdcListenerApplication {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) {
    SpringApplication.run(CdcListenerApplication.class, args);
  }

  @Bean
  Consumer<String> customerChange() {

    return message -> {

      logger.info("customerChange() - Message: {}", message);

      try {
        CdcMessage<Object, Customer> customerCdcMessage = deserializeCustomerCdcMessage(message);
        logger.info("customerChange() - customerCdcMessage: {}", customerCdcMessage);
        logger.info("customerChange() - Payload: {}", customerCdcMessage.getPayload());
        logger.info("customerChange() - Payload Operation: {}",
            customerCdcMessage.getPayload().getOp());
        logger.info("customerChange() - Payload Customer(before): {}",
            customerCdcMessage.getPayload().getBefore());
        logger.info("customerChange() - Payload Customer(after): {}",
            customerCdcMessage.getPayload().getAfter());

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
        CdcMessage<Object, Address> addressCdcMessage = deserializeAddressCdcMessage(message);
        logger.info("addressChange() - addressCdcMessage: {}", addressCdcMessage);
        logger.info("addressChange() - Payload: {}", addressCdcMessage.getPayload());
        logger.info("addressChange() - Payload Operation: {}",
            addressCdcMessage.getPayload().getOp());
        logger.info("addressChange() - Payload Address(before): {}",
            addressCdcMessage.getPayload().getBefore());
        logger.info("addressChange() - Payload Address(after): {}",
            addressCdcMessage.getPayload().getAfter());

      } catch (JsonProcessingException e) {
        logger.error("addressChange()", e);
      }
    };
  }

/*  private Customer deserializeCustomer(String message) {

    Serde<Customer> serde = DebeziumSerdes.payloadJson(Customer.class);
    serde.configure(Collections.singletonMap("from.field", "after"), false);
    Customer customer = serde.deserializer().deserialize("dbserver1.public.customer", message.getBytes(StandardCharsets.UTF_8));
    logger.info("deserializeCustomer() - Customer: {}", customer);

    return customer;

  }*/

  private CdcMessage<Object, Customer> deserializeCustomerCdcMessage(String message)
      throws JsonProcessingException {

    return objectMapper.readValue(message, new TypeReference<>() {
    });

  }

  private CdcMessage<Object, Address> deserializeAddressCdcMessage(String message)
      throws JsonProcessingException {

    return objectMapper.readValue(message, new TypeReference<>() {
    });

  }

}
