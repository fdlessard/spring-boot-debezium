package io.fdlessard.codebites.debezium.customer.listener.model;

import static io.fdlessard.codebites.debezium.customer.listener.CustomerTestUtils.buildCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.fasterxml.jackson.databind.JsonNode;
import io.fdlessard.codebites.debezium.customer.listener.TestUtils;
import java.io.IOException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class AddressTest {

  public static final String TEST_ID_STR = "id";
  public static final String TEST_VERSION_STR = "version";
  public static final String TEST_NUMBER_STR = "number";
  public static final String TEST_STREET_STR = "street";
  public static final String TEST_CITY_STR = "city";
  public static final String TEST_PROVINCE_STR = "province";
  public static final String TEST_POSTAL_CODE_STR = "postalCode";
  public static final String TEST_COUNTRY_STR = "country";

  private static ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
  private static Validator validator = vf.getValidator();

  @Test
  void addressMarshallingToJson() {
    Address address = buildAddress();
    JsonNode node = TestUtils.getObjectMapper().convertValue(address, JsonNode.class);
    assertAddressNode(address, node);
  }

  @Test
  void addressUnMarshallingFromJson() throws IOException {

    Address address = TestUtils
        .readFileIntoPojo("/Address.json", Address.class);
    assertAddress(address);
  }

  @Test
  void validateNumberNotBlank() {

    Address address = buildAddress();
    address.setNumber(null);

    Set<ConstraintViolation<Address>> violationSet = validator
        .validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "number name cannot be blank", TEST_NUMBER_STR);
    assertEquals(1, violationsCount);

    address.setNumber(RandomStringUtils.random(3, StringUtils.SPACE));

    violationSet = validator.validate(address, Default.class);
    violationsCount = TestUtils.getViolationsCount(violationSet,
        "number name cannot be blank", TEST_NUMBER_STR);

    assertEquals(1, violationsCount);
  }

  @Test
  void validateStreetNotBlank() {

    Address address = buildAddress();
    address.setStreet(null);

    Set<ConstraintViolation<Address>> violationSet = validator
        .validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "street name cannot be blank", TEST_STREET_STR);
    assertEquals(1, violationsCount);

    address.setStreet(RandomStringUtils.random(3, StringUtils.SPACE));

    violationSet = validator.validate(address, Default.class);
    violationsCount = TestUtils.getViolationsCount(violationSet,
        "street name cannot be blank", TEST_STREET_STR);

    assertEquals(1, violationsCount);
  }

  @Test
  void validateCityNotBlank() {

    Address address = buildAddress();
    address.setCity(null);

    Set<ConstraintViolation<Address>> violationSet = validator.validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "city name cannot be blank", TEST_CITY_STR);
    assertEquals(1, violationsCount);

    address.setCity(RandomStringUtils.random(3, StringUtils.SPACE));

    violationSet = validator.validate(address, Default.class);
    violationsCount = TestUtils.getViolationsCount(violationSet,
        "city name cannot be blank", TEST_CITY_STR);

    assertEquals(1, violationsCount);
  }

  @Test
  void validateCityMinSize() {

    Address address = buildAddress();
    address.setCity("a");

    Set<ConstraintViolation<Address>> violationSet = validator.validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "city must have more thant 2 characters", TEST_CITY_STR);
    assertEquals(1, violationsCount);
  }

  @Test
  void validatePostalCodeNotBlank() {

    Address address = buildAddress();
    address.setPostalCode(null);

    Set<ConstraintViolation<Address>> violationSet = validator.validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "postalCode name cannot be blank", TEST_POSTAL_CODE_STR);
    assertEquals(1, violationsCount);

    address.setPostalCode(RandomStringUtils.random(3, StringUtils.SPACE));

    violationSet = validator.validate(address, Default.class);
    violationsCount = TestUtils.getViolationsCount(violationSet,
        "postalCode name cannot be blank", TEST_POSTAL_CODE_STR);

    assertEquals(1, violationsCount);
  }

  @Test
  void validatePostalCodeMinSize() {

    Address address = buildAddress();
    address.setPostalCode("a");

    Set<ConstraintViolation<Address>> violationSet = validator.validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "postalCode must have more thant 2 characters", TEST_POSTAL_CODE_STR);
    assertEquals(1, violationsCount);
  }

  @Test
  void validateProvinceCodeNotBlank() {

    Address address = buildAddress();
    address.setProvince(null);

    Set<ConstraintViolation<Address>> violationSet = validator.validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "province name cannot be blank", TEST_PROVINCE_STR);
    assertEquals(1, violationsCount);

    address.setProvince(RandomStringUtils.random(3, StringUtils.SPACE));

    violationSet = validator.validate(address, Default.class);
    violationsCount = TestUtils.getViolationsCount(violationSet,
        "province name cannot be blank", TEST_PROVINCE_STR);

    assertEquals(1, violationsCount);
  }

  @Test
  void validateProvinceMinSize() {

    Address address = buildAddress();
    address.setProvince("a");

    Set<ConstraintViolation<Address>> violationSet = validator.validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "province must have more thant 2 characters", TEST_PROVINCE_STR);
    assertEquals(1, violationsCount);
  }

  @Test
  void validateCountryNotBlank() {

    Address address = buildAddress();
    address.setCountry(null);

    Set<ConstraintViolation<Address>> violationSet = validator.validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "country name cannot be blank", TEST_COUNTRY_STR);
    assertEquals(1, violationsCount);

    address.setCountry(RandomStringUtils.random(3, StringUtils.SPACE));

    violationSet = validator.validate(address, Default.class);
    violationsCount = TestUtils.getViolationsCount(violationSet,
        "country name cannot be blank", TEST_COUNTRY_STR);

    assertEquals(1, violationsCount);
  }

  @Test
  void validateCountryMinSize() {

    Address address = buildAddress();
    address.setCountry("a");

    Set<ConstraintViolation<Address>> violationSet = validator.validate(address, Default.class);

    long violationsCount = TestUtils.getViolationsCount(violationSet,
        "country must have more thant 2 characters", TEST_COUNTRY_STR);
    assertEquals(1, violationsCount);
  }

  @Test
  void equalsContract() {

    Customer c = buildCustomer();
    c.setId(1l);

    EqualsVerifier.forClass(Address.class)
        .withRedefinedSuperclass()
        .withPrefabValues(Customer.class, buildCustomer(), c)
        .suppress(Warning.STRICT_INHERITANCE)
        .suppress(Warning.NONFINAL_FIELDS)
        .verify();

    Address address1 = buildAddress();
    Address address2 = new Address();
    address2.setId(0l);
    address2.setVersion(0);
    address2.setNumber(TEST_NUMBER_STR);
    address2.setStreet(TEST_STREET_STR);
    address2.setCity(TEST_CITY_STR);
    address2.setPostalCode(TEST_POSTAL_CODE_STR);
    address2.setProvince(TEST_PROVINCE_STR);
    address2.setCountry(TEST_COUNTRY_STR);
    assertEquals(address1, address2);
    assertEquals(address1.toString(), address2.toString());
  }

  public static void assertAddress(Address address) {

    assertEquals(TEST_NUMBER_STR, address.getNumber());
    assertEquals(TEST_STREET_STR, address.getStreet());
    assertEquals(TEST_CITY_STR, address.getCity());
    assertEquals(TEST_PROVINCE_STR, address.getProvince());
    assertEquals(TEST_POSTAL_CODE_STR, address.getPostalCode());
    assertEquals(TEST_COUNTRY_STR, address.getCountry());
  }

  public static void assertAddressNode(Address address, JsonNode node) {
    assertEquals(address.getId(), node.get(TEST_ID_STR).asInt());
    assertFalse(node.has(TEST_VERSION_STR));
    assertEquals(address.getNumber(), node.get(TEST_NUMBER_STR).asText());
    assertEquals(address.getStreet(), node.get(TEST_STREET_STR).asText());
    assertEquals(address.getCity(), node.get(TEST_CITY_STR).asText());
    assertEquals(address.getProvince(), node.get(TEST_PROVINCE_STR).asText());
    assertEquals(address.getPostalCode(), node.get(TEST_POSTAL_CODE_STR).asText());
    assertEquals(address.getProvince(), node.get(TEST_PROVINCE_STR).asText());
    assertEquals(address.getCountry(), node.get(TEST_COUNTRY_STR).asText());
  }

  public static Address buildAddress() {

    return Address.builder()
        .id(0l)
        .version(0)
        .number(TEST_NUMBER_STR)
        .street(TEST_STREET_STR)
        .city(TEST_CITY_STR)
        .postalCode(TEST_POSTAL_CODE_STR)
        .province(TEST_PROVINCE_STR)
        .country(TEST_COUNTRY_STR)
        .build();
  }

}