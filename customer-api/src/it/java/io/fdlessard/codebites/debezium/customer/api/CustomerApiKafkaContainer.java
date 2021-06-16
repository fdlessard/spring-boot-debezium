package io.fdlessard.codebites.debezium.customer.api;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class CustomerApiKafkaContainer {

  private static KafkaContainer kafkaContainer = new KafkaContainer(
      DockerImageName.parse("confluentinc/cp-kafka")
  );

  public static KafkaContainer getInstance() {
    return kafkaContainer;
  }

}
