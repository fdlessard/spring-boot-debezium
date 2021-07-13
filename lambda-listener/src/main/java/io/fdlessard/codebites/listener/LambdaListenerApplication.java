package io.fdlessard.codebites.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class LambdaListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LambdaListenerApplication.class, args);
    }


    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }

}
