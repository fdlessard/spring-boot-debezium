package io.fdlessard.codebites.debezium.lambda;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;

public final class Utils {

    private static ObjectMapper objectMapper;

    private Utils() {
    }

    public static ObjectMapper getObjectMapper() {
        if(objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        }

        return objectMapper;
    }
}
