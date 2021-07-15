package io.fdlessard.codebites.debezium.lambda.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Customer implements Serializable {

  @JsonCreator
  public Customer(
      @JsonProperty("id") Long id,
      @JsonProperty("version") int version,
      @JsonProperty("first_name") String firstName,
      @JsonProperty("last_name") String lastName,
      @JsonProperty("company") String company
  ) {
    this.id = id;
    this.version = version;
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
  }

  private Long id;

  private int version;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("first_name")
  private String firstName;

  private String company;

}
