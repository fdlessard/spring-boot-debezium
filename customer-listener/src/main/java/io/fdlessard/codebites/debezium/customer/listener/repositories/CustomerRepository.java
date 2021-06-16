package io.fdlessard.codebites.debezium.customer.listener.repositories;

import io.fdlessard.codebites.debezium.customer.listener.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
