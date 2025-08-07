package com.sixbank.notificationlibrary.shared.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * JSON utility methods for notification processing.
 * Provides methods for serializing/deserializing objects, parsing JSON, and converting between JSON structures and maps.
 */
public final class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // Support Java 8 date/time types
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Serialize dates as ISO-8601

    /**
     * Serializes an object into its JSON string representation.
     *
     * @param object the object to serialize
     * @return the JSON string
     * @throws RuntimeException if serialization fails
     */
    public static String toJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize object to JSON", e);
            throw new RuntimeException("JSON serialization failed", e);
        }
    }

    /**
     * Deserializes a JSON string into an object of the specified type.
     *
     * @param json  the JSON string
     * @param clazz the target class type
     * @param <T>   the type of the object to return
     * @return the deserialized object
     * @throws RuntimeException if deserialization fails
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize JSON to object", e);
            throw new RuntimeException("JSON deserialization failed", e);
        }
    }

    /**
     * Parses a JSON string into a JsonNode tree model.
     *
     * @param json the JSON string
     * @return the JsonNode representation
     * @throws RuntimeException if parsing fails
     */
    public static JsonNode parseJson(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse JSON", e);
            throw new RuntimeException("JSON parsing failed", e);
        }
    }

    /**
     * Converts a Map into a JsonNode structure.
     *
     * @param map the map to convert
     * @return the resulting JsonNode
     */
    public static JsonNode mapToJsonNode(Map<String, Object> map) {
        return MAPPER.valueToTree(map);
    }

    /**
     * Converts a JsonNode into a Map.
     *
     * @param node the JsonNode to convert
     * @return the resulting Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonNodeToMap(JsonNode node) {
        return MAPPER.convertValue(node, Map.class);
    }

    /**
     * Converts an object into a human-readable JSON string with indentation.
     *
     * @param object the object to pretty-print
     * @return the pretty-printed JSON string
     */
    public static String prettyPrint(Object object) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to pretty print object", e);
            return object.toString();
        }
    }

    /**
     * Private constructor to prevent instantiation.
     * This class is a utility class and should not be instantiated.
     */
    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }
}

