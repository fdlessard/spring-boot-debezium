package io.fdlessard.codebites.debezium.customer.listener.services;

import io.fdlessard.codebites.debezium.customer.listener.model.Customer;
import io.fdlessard.codebites.debezium.customer.listener.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

  private CustomerRepository customerRepository;

  public Customer createCustomer(Customer customer) {

    Customer createdCustomer = customerRepository.save(customer);
    return createdCustomer;
  }

}
