package io.fdlessard.codebites.debezium.cdc.listener;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Slf4j
public class TestUtils {

  public static final String STRING_TO_POJO_ERROR = "Issue in stringToPojo()";
  public static final String POJO_TO_STRING_ERROR = "Issue in pojoToString()";

  private static ObjectMapper objectMapper;

  public static String readFileIntoString(String fileName) {
    return new Scanner(TestUtils.class.getResourceAsStream(fileName)).useDelimiter("\\Z")
        .next();
  }

  public static <T> T readFileIntoPojo(String fileName, Class<T> classType) throws IOException {
    String s = readFileIntoString(fileName);
    return getObjectMapper().readValue(s, classType);
  }

  public static <T> T stringToPojo(String s, Class<T> classType) {
    T pojo = null;
    try {
      pojo = getObjectMapper().readValue(s, classType);
    } catch (IOException e) {
      logger.error(STRING_TO_POJO_ERROR, e);
    }
    return pojo;
  }

  public static <T> T stringToPojo(String s, Class<T> classType, Class<?> view) {
    T pojo = null;
    try {
      pojo = getObjectMapper().readerWithView(view).forType(classType).readValue(s);
    } catch (IOException e) {
      logger.error(STRING_TO_POJO_ERROR, e);
    }
    return pojo;
  }

  public static <T> T stringToPojo(String s, ObjectMapper mapper, Class<T> classType) {
    T pojo = null;
    try {
      pojo = mapper.readValue(s, classType);
    } catch (IOException e) {
      logger.error(STRING_TO_POJO_ERROR, e);
    }
    return pojo;
  }

  public static <T> T stringToPojo(String s, ObjectMapper mapper, Class<T> classType,
      Class<?> view) {
    T pojo = null;
    try {
      pojo = mapper.readerWithView(view).forType(classType).readValue(s);
    } catch (IOException e) {
      logger.error(STRING_TO_POJO_ERROR, e);
    }
    return pojo;
  }

  public static String pojoToString(Object o) {
    String s = StringUtils.EMPTY;
    try {
      s = getObjectMapper().writeValueAsString(o);
    } catch (IOException e) {
      logger.error(POJO_TO_STRING_ERROR, e);
    }
    return s;
  }

  public static String pojoToString(ObjectMapper mapper, Object o) {
    String s = StringUtils.EMPTY;
    try {
      s = mapper.writeValueAsString(o);
    } catch (IOException e) {
      logger.error(POJO_TO_STRING_ERROR, e);
    }
    return s;
  }

  public static <T> List<T> readFileIntoPojoList(String fileName, Class<T> classType)
      throws IOException {
    String s = readFileIntoString(fileName);
    JavaType type = getObjectMapper().getTypeFactory()
        .constructCollectionType(List.class, classType);
    return getObjectMapper().readValue(s, type);
  }

  public static ObjectMapper getObjectMapper() {

    if (objectMapper == null) {
      objectMapper = Jackson2ObjectMapperBuilder.json()
          //       .modulesToInstall(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
          .serializationInclusion(Include.NON_EMPTY)
//        .featuresToEnable(Feature.WRITE_BIGDECIMAL_AS_PLAIN)
          .build();
    }

    return objectMapper;
  }

  public static <T> long getViolationsCount(
      Set<ConstraintViolation<T>> violationSet,
      String message,
      String propertyPath) {
    return violationSet.stream()
        .filter(
            x -> x.getMessageTemplate().equals(message))
        .filter(x -> x.getPropertyPath().toString().equals(propertyPath))
        .count();
  }

  // private constructor since this is a Utils class
  private TestUtils() {
  }

}