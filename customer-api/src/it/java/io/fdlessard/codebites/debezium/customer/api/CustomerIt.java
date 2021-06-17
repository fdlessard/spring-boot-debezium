package io.fdlessard.codebites.debezium.customer.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@ExtendWith({SpringExtension.class})
public class CustomerIt extends BaseIt {

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .build();
    }

    @Test
    void getCustomerById() throws Exception {

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void createCustomer() throws Exception {

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(buildJsonStringCustomer()))
                .andExpect(status().is2xxSuccessful());
    }

    static String buildJsonStringCustomer() {
        return TestUtils.readFileIntoString("/Customer.json");
    }

}
