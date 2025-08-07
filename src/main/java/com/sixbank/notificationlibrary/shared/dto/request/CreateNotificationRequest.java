package com.sixbank.notificationlibrary.shared.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sixbank.notificationlibrary.shared.enums.NotificationEventType;
import com.sixbank.notificationlibrary.shared.enums.NotificationPriority;
import com.sixbank.notificationlibrary.shared.validations.ValidCustomerId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) representing a request to create a new notification.
 * <p>
 * This object captures all necessary information to generate a notification
 * including the event type, the customer receiving the notification, payload data,
 * priority, scheduling, expiration, and additional metadata.
 * </p>
 *
 * <h3>Field Descriptions:</h3>
 * <ul>
 *   <li><b>eventType:</b> Type of notification event (e.g., PAYMENT_CONFIRMATION)</li>
 *   <li><b>customerId:</b> Unique identifier for the recipient customer</li>
 *   <li><b>payload:</b> Dynamic data content associated with the notification</li>
 *   <li><b>priority:</b> Notification priority affecting delivery urgency (defaults to MEDIUM)</li>
 *   <li><b>sourceService:</b> Optional string indicating the originating service name</li>
 *   <li><b>scheduledAt:</b> Optional UTC timestamp for when notification should be sent</li>
 *   <li><b>expiresAt:</b> Optional UTC timestamp after which notification is no longer valid</li>
 *   <li><b>metadata:</b> Optional additional key-value metadata for extensibility</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * CreateNotificationRequest request = CreateNotificationRequest.builder()
 *     .eventType(NotificationEventType.ACCOUNT_CREATED)
 *     .customerId("cus_123456")
 *     .payload(Map.of("firstName", "Jane", "accountNumber", "12345"))
 *     .priority(NotificationPriority.HIGH)
 *     .sourceService("account-service")
 *     .scheduledAt(Instant.now().plusSeconds(60)) // schedule for 1 minute later
 *     .build();
 * }</pre>
 */
public class CreateNotificationRequest {

    /**
     * The type of notification event.
     * Must be a valid {@link NotificationEventType}.
     */
    @NotNull(message = "Event type is required")
    @ValidNotificationType
    private NotificationEventType eventType;

    /**
     * The ID of the customer to whom the notification applies.
     */
    @NotBlank(message = "Customer ID is required")
    @ValidCustomerId
    private String customerId;

    /**
     * The dynamic payload data for the notification.
     * This can contain any event-specific information as key-value pairs.
     */
    @NotNull(message = "Payload is required")
    @Valid
    private Map<String, Object> payload;

    /**
     * Priority level of the notification affecting delivery urgency.
     * Defaults to {@link NotificationPriority#MEDIUM}.
     */
    private NotificationPriority priority = NotificationPriority.MEDIUM;

    /**
     * Optional identifier of the source service creating the notification.
     * Limited to 100 characters.
     */
    @Size(max = 100, message = "Source service name cannot exceed 100 characters")
    private String sourceService;

    /**
     * Optional UTC timestamp when the notification should be scheduled for delivery.
     * Should be serialized/deserialized in ISO 8601 format with milliseconds.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant scheduledAt;

    /**
     * Optional UTC timestamp after which the notification should expire and not be delivered.
     * Should be serialized/deserialized in ISO 8601 format with milliseconds.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant expiresAt;

    /**
     * Optional metadata map for additional extensible information related to the notification.
     */
    private Map<String, String> metadata;

    // Constructors

    public CreateNotificationRequest() {}

    private CreateNotificationRequest(Builder builder) {
        this.eventType = builder.eventType;
        this.customerId = builder.customerId;
        this.payload = builder.payload;
        this.priority = builder.priority;
        this.sourceService = builder.sourceService;
        this.scheduledAt = builder.scheduledAt;
        this.expiresAt = builder.expiresAt;
        this.metadata = builder.metadata;
    }

    // Builder pattern for convenient object construction
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private NotificationEventType eventType;
        private String customerId;
        private Map<String, Object> payload;
        private NotificationPriority priority = NotificationPriority.MEDIUM;
        private String sourceService;
        private Instant scheduledAt;
        private Instant expiresAt;
        private Map<String, String> metadata;

        public Builder eventType(NotificationEventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder payload(Map<String, Object> payload) {
            this.payload = payload;
            return this;
        }

        public Builder priority(NotificationPriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder sourceService(String sourceService) {
            this.sourceService = sourceService;
            return this;
        }

        public Builder scheduledAt(Instant scheduledAt) {
            this.scheduledAt = scheduledAt;
            return this;
        }

        public Builder expiresAt(Instant expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public CreateNotificationRequest build() {
            return new CreateNotificationRequest(this);
        }
    }

    // Getters and Setters

    public NotificationEventType getEventType() {
        return eventType;
    }

    public void setEventType(NotificationEventType eventType) {
        this.eventType = eventType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public NotificationPriority getPriority() {
        return priority;
    }

    public void setPriority(NotificationPriority priority) {
        this.priority = priority;
    }

    public String getSourceService() {
        return sourceService;
    }

    public void setSourceService(String sourceService) {
        this.sourceService = sourceService;
    }

    public Instant getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Instant scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    // equals, hashCode, toString omitted for brevity but can remain unchanged
}

