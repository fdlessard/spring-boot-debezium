package io.fdlessard.codebites.debezium.cdc.listener.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

  private Long id;

  private int version;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("first_name")
  private String firstName;

  private String company;

}
