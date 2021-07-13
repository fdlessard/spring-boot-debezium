package io.fdlessard.codebites.debezium.lambda;

import io.fdlessard.codebites.debezium.lambda.model.Client;
import io.fdlessard.codebites.debezium.lambda.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class LambdaListenerApplication {

    @Autowired
    private ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(LambdaListenerApplication.class, args);
    }

    @Bean
    public Function<String, String> reverseString() {
        Client client = new Client(null, 0, "FirstNameLambda", "LastNameLambda", "CompanyLamba");
        insertClient(client);
        return value -> new StringBuilder(value).reverse().toString();
    }

    private void insertClient(Client client) {

        clientRepository.save(client);
    }

}
