package io.fdlessard.codebites.debezium.cdc.listener.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class DebeziumCdcMessage<S,D> {

  private S schema;

  private Payload<D>  payload;

  @JsonCreator
  DebeziumCdcMessage(
      @JsonProperty("schema") S schema,
      @JsonProperty("payload") Payload<D>  payload){
    this.schema=schema;
    this.payload=payload;
  }

/*  public S getSchema() {
    return schema;
  }

  public void setSchema(S schema) {
    this.schema = schema;
  }

  public DebeziumPayload<D> getPayload() {
    return payload;
  }

  public void setPayload(DebeziumPayload<D> payload) {
    this.payload = payload;
  }*/


}
