package com.thesquad.microservice.moviesservice.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Utility class for converting a JSON to a give class
 *
 * @param <T> - The class that we wish to return
 * @author Atanas Asprovski
 * @version 1.0
 */
@Component
public class JsonUtils {
    public <T> T jsonConverter(String message, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T object = mapper.readValue(message, valueType);
            System.out.println(object);
            return object;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }
}