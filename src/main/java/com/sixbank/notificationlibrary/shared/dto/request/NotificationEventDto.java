package com.sixbank.notificationlibrary.shared.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sixbank.notificationlibrary.shared.enums.NotificationEventType;
import com.sixbank.notificationlibrary.shared.enums.NotificationPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

/**
 * Data Transfer Object representing a notification event sent through Kafka.
 * <p>
 * This DTO contains information about the event such as type, customer, source service,
 * priority, and payload data. It also supports optional metadata for tracing and correlation.
 * </p>
 */
public class NotificationEventDto {

    /** Unique identifier of the notification event */
    @NotBlank(message = "Event ID is required")
    private String eventId;

    /** Type/category of the notification event */
    @NotNull(message = "Event type is required")
    private NotificationEventType eventType;

    /** Identifier of the customer related to this event */
    @NotBlank(message = "Customer ID is required")
    private String customerId;

    /** The service that originated this event */
    @NotBlank(message = "Source service is required")
    private String sourceService;

    /** Priority of the notification event, defaults to MEDIUM */
    private NotificationPriority priority = NotificationPriority.MEDIUM;

    /** Payload data containing event-specific information */
    @NotNull(message = "Payload is required")
    private Map<String, Object> payload;

    /** Timestamp when the event was created or sent */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant timestamp;

    /** Correlation ID for tracing distributed transactions */
    private String correlationId;

    /** Trace ID for distributed tracing */
    private String traceId;

    /** Additional metadata as key-value pairs */
    private Map<String, String> metadata;

    /**
     * Default constructor initializing the timestamp to current time.
     */
    public NotificationEventDto() {
        this.timestamp = Instant.now();
    }

    /**
     * Constructs a NotificationEventDto with required fields.
     *
     * @param eventId       unique event identifier
     * @param eventType     type of the notification event
     * @param customerId    customer identifier
     * @param sourceService originating service name
     * @param payload       event-specific payload data
     */
    public NotificationEventDto(String eventId, NotificationEventType eventType,
                                String customerId, String sourceService,
                                Map<String, Object> payload) {
        this();
        this.eventId = eventId;
        this.eventType = eventType;
        this.customerId = customerId;
        this.sourceService = sourceService;
        this.payload = payload;
    }

    // Getters and Setters

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public NotificationEventType getEventType() { return eventType; }
    public void setEventType(NotificationEventType eventType) { this.eventType = eventType; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getSourceService() { return sourceService; }
    public void setSourceService(String sourceService) { this.sourceService = sourceService; }

    public NotificationPriority getPriority() { return priority; }
    public void setPriority(NotificationPriority priority) { this.priority = priority; }

    public Map<String, Object> getPayload() { return payload; }
    public void setPayload(Map<String, Object> payload) { this.payload = payload; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public String getCorrelationId() { return correlationId; }
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }

    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }

    public Map<String, String> getMetadata() { return metadata; }
    public void setMetadata(Map<String, String> metadata) { this.metadata = metadata; }

    /**
     * Equality is based on eventId only.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationEventDto that = (NotificationEventDto) o;
        return Objects.equals(eventId, that.eventId);
    }

    /**
     * Hashcode is based on eventId only.
     */
    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    /**
     * String representation of NotificationEventDto for logging/debugging.
     */
    @Override
    public String toString() {
        return "NotificationEventDto{" +
                "eventId='" + eventId + '\'' +
                ", eventType=" + eventType +
                ", customerId='" + customerId + '\'' +
                ", sourceService='" + sourceService + '\'' +
                ", priority=" + priority +
                ", timestamp=" + timestamp +
                '}';
    }
}

