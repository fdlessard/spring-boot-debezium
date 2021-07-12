package io.fdlessard.codebites.debezium.cdc.listener.model;

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
public class Address implements Serializable {

  private Long id;

  private int version;

  @JsonProperty("customer_id")
  private Long customerId;

  private String number;

  private String street;

  private String city;

  private String postalCode;

  private String province;

  private String country;

}
