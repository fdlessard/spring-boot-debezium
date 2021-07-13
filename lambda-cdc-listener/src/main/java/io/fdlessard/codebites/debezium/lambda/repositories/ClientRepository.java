package io.fdlessard.codebites.debezium.lambda.repositories;

import io.fdlessard.codebites.debezium.lambda.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

}
