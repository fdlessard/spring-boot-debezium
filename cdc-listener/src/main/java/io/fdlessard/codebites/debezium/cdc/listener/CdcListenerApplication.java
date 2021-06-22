package io.fdlessard.codebites.debezium.cdc.listener;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
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
    };
  }

  @Bean
  Consumer<String> addressChange() {
    return message -> {
      logger.info("addressChange() - Message: {}", message);
    };
  }

}
