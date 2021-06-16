package io.fdlessard.codebites.debezium.customer.listener.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "address", schema = "public")
@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity implements Serializable {

  @JoinColumn(name = "customer_id")
  private Long customerId;

  @NotBlank(message = "number name cannot be blank")
  @Column(name = "number")
  private String number;

  @NotBlank(message = "street name cannot be blank")
  @Size(min = 2, message = "street must have more thant 2 characters")
  @Column(name = "street")
  private String street;

  @NotBlank(message = "city name cannot be blank")
  @Size(min = 2, message = "city must have more thant 2 characters")
  @Column(name = "city")
  private String city;

  @NotBlank(message = "postalCode name cannot be blank")
  @Size(min = 2, message = "postalCode must have more thant 2 characters")
  @Column(name = "postal_code")
  private String postalCode;

  @NotBlank(message = "province name cannot be blank")
  @Size(min = 2, message = "province must have more thant 2 characters")
  @Column(name = "province")
  private String province;

  @NotBlank(message = "country name cannot be blank")
  @Size(min = 2, message = "country must have more thant 2 characters")
  @Column(name = "country")
  private String country;

}
