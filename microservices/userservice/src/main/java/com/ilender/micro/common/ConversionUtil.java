package com.ilender.micro.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.Message;

import java.io.IOException;

public class ConversionUtil {

    public static String objectToJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }


    public static String jmsMessageInJson(final Message message) {
        String jsonString = null;
        try {
            jsonString = message.getPayload().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static Object jsonToObject(String jsonString, Class className) {
        Object result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            result = objectMapper.readValue(jsonString, className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
