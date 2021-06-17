package io.fdlessard.codebites.debezium.customer.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
@ExtendWith({SpringExtension.class})
public abstract class BaseIt {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

}
