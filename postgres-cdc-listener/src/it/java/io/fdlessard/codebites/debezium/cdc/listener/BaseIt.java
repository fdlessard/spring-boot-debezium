package io.fdlessard.codebites.debezium.cdc.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
//import org.testcontainers.containers.KafkaContainer;

@Slf4j
//@SpringBootTest
//@ExtendWith({SpringExtension.class})
public abstract class BaseIt {

  @Autowired
  protected WebApplicationContext wac;

  protected MockMvc mockMvc;

/*  public static KafkaContainer kafkaContainer = CustomerProcessorKafkaContainer.getInstance();

  @DynamicPropertySource
  static void registerKafkaProperties(DynamicPropertyRegistry registry) {
    kafkaContainer.start();
    logger.info("Property spring.kafka.bootstrap-servers: " + kafkaContainer.getBootstrapServers());
    registry.add("spring.kafka.bootstrap-servers", () -> kafkaContainer.getBootstrapServers());
  }*/

}
