package io.fdlessard.codebites.debezium.lambda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.fdlessard.codebites.debezium.lambda.dtos.CdcMessage;
import io.fdlessard.codebites.debezium.lambda.dtos.Customer;
import io.fdlessard.codebites.debezium.lambda.dtos.LambdaPayload;
import io.fdlessard.codebites.debezium.lambda.model.Client;
import io.fdlessard.codebites.debezium.lambda.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@SpringBootApplication
public class LambdaListenerApplication {

    @Autowired
    private ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(LambdaListenerApplication.class, args);
    }

    @Bean
    public Function<List<Map>, String> handleLambdaPayloads() {
        return this::handleLambdaPayloads;
    }

    private String handleLambdaPayloads(List<Map> lambdaPayloads) {

        log.info("LambdaListenerApplication.handleLambdaPayloads() - lambdaPayloads: {} ", lambdaPayloads);
        Map firstPayload = lambdaPayloads.get(0);
        log.info("LambdaListenerApplication.handleLambdaPayloads() - firstPayload: {} ", firstPayload);

        LambdaPayload lambdaPayload = Utils.getObjectMapper().convertValue(firstPayload.get("payload"), LambdaPayload.class);

        log.info("LambdaListenerApplication.handleLambdaPayloads() - lambdaPayload: {} ", lambdaPayload);

        String value = lambdaPayload.getValue();
        log.info("LambdaListenerApplication.handleLambdaPayloads() - value: {} ", value);

        Map customer = Utils.getObjectMapper().convertValue(value, Map.class);
        log.info("LambdaListenerApplication.handleLambdaPayloads() - customer: {} ", customer);



/*        try {
            CdcMessage<Customer> customerCdcMessage = deserializeCustomerCdcMessage(cdcMessage);
            var client = toClient(customerCdcMessage.getAfter());
            var c = clientRepository.save(client);
            return c.toString();*
        } catch (JsonProcessingException e) {
            log.error("LambdaListenerApplication.handleCustomerCdcMessage() - Error: {} ", e);
            return e.getMessage();
        }*/

/*          try {
              Customer customer = Utils.getObjectMapper().readValue(value, Customer.class);
              log.info("LambdaListenerApplication.handleLambdaPayloads() - customer: {} ", customer);
              return customer.toString();
        } catch (JsonProcessingException e) {
            log.error("LambdaListenerApplication.handleLambdaPayloads() - Error: {} ", e);
            return e.getMessage();
        }*/

        return "allo";

    }

    private static Client toClient(Customer customer) {
        var client = new Client();
        client.setFirstName(customer.getFirstName());
        client.setLastName(customer.getLastName());
        client.setCompany(customer.getCompany());
        client.setNewEntity(true);
        return client;
    }

/*    public static LambdaPayload deserializeLambdaPayload(Map message)
            throws JsonProcessingException {

        return Utils.getObjectMapper().readValue(message, LambdaPayload.class);

    }*/

    private static CdcMessage<Customer> deserializeCustomerCdcMessage(String message)
            throws JsonProcessingException {

        return Utils.getObjectMapper().readValue(message, new TypeReference<>() {
        });

    }

}
