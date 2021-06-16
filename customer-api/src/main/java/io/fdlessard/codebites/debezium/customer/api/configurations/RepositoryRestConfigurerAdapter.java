package io.fdlessard.codebites.debezium.customer.api.configurations;


import io.fdlessard.codebites.debezium.customer.api.model.Address;
import io.fdlessard.codebites.debezium.customer.api.model.Customer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryRestConfigurerAdapter implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration repositoryRestConfiguration,
      CorsRegistry corsRegistry) {

    repositoryRestConfiguration.exposeIdsFor(
        Customer.class,
        Address.class
    );
  }

}
