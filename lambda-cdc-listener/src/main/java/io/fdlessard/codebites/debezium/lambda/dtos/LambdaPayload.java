package io.fdlessard.codebites.debezium.lambda.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LambdaPayload implements Serializable {

    private String headers;
    private String key;
    private String value;
    private String topic;
    private Long timestamp;
    private Integer partition;
    private Integer offset;

}
