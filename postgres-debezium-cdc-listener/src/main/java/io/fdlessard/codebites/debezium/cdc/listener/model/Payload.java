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
public class Payload<D> {

  private D before;

  private D after;

  private String op;

  private String ts_ms;

  private Object source;


  @JsonCreator
  public Payload(
      @JsonProperty("before") D before,
      @JsonProperty("after") D after,
      @JsonProperty("op") String op,
      @JsonProperty("ts_ms") String ts_ms,
      @JsonProperty("source") Object source
  ) {
    this.before = before;
    this.after = after;
    this.op = op;
    this.ts_ms = ts_ms;
    this.setSource(source);
  }

/*  public D getBefore() {
    return before;
  }

  public void setBefore(D before) {
    this.before = before;
  }

  public D getAfter() {
    return after;
  }

  public void setAfter(D after) {
    this.after = after;
  }

  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }

  public String getTs_ms() {
    return ts_ms;
  }

  public void setTs_ms(String ts_ms) {
    this.ts_ms = ts_ms;
  }

  public Object getSource() {
    return source;
  }

  public void setSource(Object source) {
    this.source = source;
  }*/


}